package ua.pidopryhora.mediaconverter.requestmanager.service.sqs;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import ua.pidopryhora.mediaconverter.requestmanager.service.EventProcessor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SqsPoller {

    private final SqsClient sqsClient;
    private final EventProcessor eventProcessor;

    @Value("${sqs.queue.url}")
    private String queueUrl;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private volatile boolean running = true;

    public SqsPoller(SqsClient sqsClient, EventProcessor eventProcessor) {
        this.sqsClient = sqsClient;
        this.eventProcessor = eventProcessor;
    }

    @PostConstruct
    public void init() {
        log.debug("Starting SQS Poller...");
        executorService.submit(this::poll);
    }

    public void poll() {
        while (running) {
            try {
                ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .maxNumberOfMessages(10)
                        .waitTimeSeconds(10) // Long polling
                        .build();

                List<Message> messages = sqsClient.receiveMessage(request).messages();

                for (Message message : messages) {
                    processAndDeleteMessage(message);
                }

            } catch (Exception e) {
                if (!running) {
                    break;
                }
                log.error("Error while polling SQS queue: ", e);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void processAndDeleteMessage(Message message) {
        try {
            eventProcessor.processMessage(message.body());
        } catch (Exception e) {
            log.error("Failed to process message with receipt handle {}: ", message.receiptHandle(), e);
        } finally {
            deleteMessage(message.receiptHandle());
        }
    }

    private void deleteMessage(String receiptHandle) {
        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
        try {
            sqsClient.deleteMessage(deleteRequest);
            log.debug("Deleted message with receipt handle: {}", receiptHandle);
        } catch (Exception e) {
            log.error("Failed to delete message with receipt handle {}: ", receiptHandle, e);
        }
    }

    @PreDestroy
    public void shutdown() {
        log.debug("Shutting down SQS Poller...");
        running = false;
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        sqsClient.close();
    }
}