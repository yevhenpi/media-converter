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
    private final UploadRequestCachingService uploadRequestCachingService;
    private final FileDataService fileDataService;


    public void processMessage(String messageBody) throws JsonProcessingException {


        S3Event event = objectMapper.readValue(messageBody, S3Event.class);

        for (S3Event.S3Record record : event.getRecords()) {
            String objectKey = record.getS3().getObject().getKey();
            Integer objectSize = record.getS3().getObject().getSize();


            UploadRequestDTO requestDTO = uploadRequestCachingService.getFileData(objectKey);
            if(!isUploadValid(objectSize, requestDTO)){
                log.debug("INVALID UPLOAD");
                log.debug(messageBody);
                return;
            }

            fileDataService.saveFile(requestDTO);
            log.debug("File is ready");
            uploadRequestCachingService.removeFileData(objectKey);

            log.debug(messageBody);

        }

    }

    private boolean isUploadValid(Integer size, UploadRequestDTO requestDTO){
        return size.longValue() == requestDTO.getFileSize();
    }
}
