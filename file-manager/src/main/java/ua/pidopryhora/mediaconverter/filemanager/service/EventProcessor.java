package ua.pidopryhora.mediaconverter.filemanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.S3Event;
@Slf4j
@Service
public class EventProcessor {


    private final ObjectMapper objectMapper;

    public EventProcessor() {
        this.objectMapper = new ObjectMapper();
    }

    public void processMessage(String messageBody) throws JsonProcessingException {

        // Parse the SQS message body
        S3Event event = objectMapper.readValue(messageBody, S3Event.class);


        for (S3Event.S3Record record : event.getRecords()) {
            String bucketName = record.getS3().getBucket().getName();
            String objectKey = record.getS3().getObject().getKey();
            String eventName = record.getEventName();



            log.debug("File uploaded: {} in bucket: {}", objectKey, bucketName);
            log.debug("Event type: {}", eventName);

            log.debug(messageBody);

            // Add custom logic here, e.g., start a file processing workflow
        }

    }
}
