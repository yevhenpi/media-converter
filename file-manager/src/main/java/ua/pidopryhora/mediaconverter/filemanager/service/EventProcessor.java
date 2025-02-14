package ua.pidopryhora.mediaconverter.filemanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.S3Event;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventProcessor {


    private final ObjectMapper objectMapper;
    private final CashingService cashingService;


    public void processMessage(String messageBody) throws JsonProcessingException {

        // Parse the SQS message body
        S3Event event = objectMapper.readValue(messageBody, S3Event.class);


        for (S3Event.S3Record record : event.getRecords()) {
            String bucketName = record.getS3().getBucket().getName();
            String objectKey = record.getS3().getObject().getKey();
            String eventName = record.getEventName();
            Integer objectSize = record.getS3().getObject().getSize();



            log.debug("File uploaded: {} in bucket: {}", objectKey, bucketName);
            log.debug("Event type: {}", eventName);

            UploadRequestDTO requestDTO = cashingService.getMetadata(objectKey);
            if(!isUploadValid(objectSize, requestDTO)){
                log.debug("INVALID UPLOAD");
                return;
            }
            log.debug("File is being processed");
            cashingService.removeMetadata(objectKey);


            log.debug(messageBody);

            // Add custom logic here, e.g., start a file processing workflow
        }

    }

    private boolean isUploadValid(Integer size, UploadRequestDTO requestDTO){
        return size.longValue() == requestDTO.getFileSize();
    }
}
