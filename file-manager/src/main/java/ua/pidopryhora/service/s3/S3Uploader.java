package ua.pidopryhora.service.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class S3Uploader {

    private final S3Client s3Client;

    public S3Uploader(S3Client s3Client){
        this.s3Client = s3Client;
    }

    @Async
    public CompletableFuture<Boolean> upload(Path filePath){
        log.info("File {} is being uploaded to s3", filePath.getFileName());
        try {
            byte[] fileContent = Files.readAllBytes(filePath);
            String bucketName = "amzn-s3-converter-bucket";
            String name = filePath.getFileName().toString();


            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(name)
                            .build(),
                    RequestBody.fromBytes(fileContent)

            );

            log.info("File {} is uploaded to s3", name);
            return CompletableFuture.completedFuture(true);
        } catch (IOException e) {
            log.error("ERROR LOADING FILE TO S3", e);
            return CompletableFuture.completedFuture(false);
        }

    }
}
