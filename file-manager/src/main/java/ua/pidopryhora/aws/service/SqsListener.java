package ua.pidopryhora.aws.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;
@Slf4j
@Service
public class SqsListener {

    private final SqsClient sqsClient;
    private final EventProcessor eventProcessor;
    @Value("${sqs.queue.url}")
    private String queueUrl;


    public SqsListener(SqsClient sqsClient,EventProcessor eventProcessor){
        this.sqsClient = sqsClient;
        this.eventProcessor = eventProcessor;
    }

    @PostConstruct
    public void startListening() {
        log.info("Starting SQS listener...");
        new Thread(this::listen).start(); // Run listener in a separate thread
    }

    private void listen() {
        while (true) {
            try {
                ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .maxNumberOfMessages(10)
                        .waitTimeSeconds(10) // Long polling
                        .build();

                List<Message> messages = sqsClient.receiveMessage(request).messages();

                for (Message message : messages) {
                    try {
                        eventProcessor.processMessage(message.body());
                        deleteMessage(message.receiptHandle());
                    } catch (Exception e) {
                        log.error("Failed to process message: " , e);
                    }
                }

            } catch (Exception e) {
                log.error("Error while listening to SQS queue: " ,e);
                return;
            }
        }
    }

    private void deleteMessage(String receiptHandle) {
        DeleteMessageRequest request = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
        sqsClient.deleteMessage(request);
        log.info("Deleted message with receipt handle: {}", receiptHandle);
    }


}
