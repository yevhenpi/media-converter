package ua.pidopryhora.aws.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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
    private Thread listenerThread;


    public SqsListener(SqsClient sqsClient,EventProcessor eventProcessor){
        this.sqsClient = sqsClient;
        this.eventProcessor = eventProcessor;
    }

    @PostConstruct
    public void init() {
        log.debug("Starting SQS Listener...");
        listenerThread = new Thread(this::listen);
        listenerThread.start();
    }

    @PreDestroy
    public void shutdown() {
        if (listenerThread != null && listenerThread.isAlive()) {
            listenerThread.interrupt();
        }
        sqsClient.close();
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

            }
            catch (Exception e) {
                if(Thread.currentThread().isInterrupted()) {
                    log.debug("Shutting down SQS Listener...");
                    break;
                }

                log.error("Error while listening to SQS queue: " ,e);
                break;
            }
        }
    }

    private void deleteMessage(String receiptHandle) {
        DeleteMessageRequest request = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
        sqsClient.deleteMessage(request);
        log.debug("Deleted message with receipt handle: {}", receiptHandle);
    }


}
