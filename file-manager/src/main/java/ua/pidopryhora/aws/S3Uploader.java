package ua.pidopryhora.aws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
@Slf4j
@Component
public class S3Uploader {

    private final S3Client s3Client;

    public S3Uploader(S3Client s3Client){
        this.s3Client = s3Client;
    }

    public void upload(MultipartFile file){
        String bucketName = "amzn-s3-converter-bucket";
        String name = file.getOriginalFilename();


        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(name)
                            .build(),
                    RequestBody.fromBytes(file.getBytes())

            );
        } catch (IOException e) {
            log.error("ERROR UPLOADING FILE TO S3", e);
        }
        log.info("File is uploaded to s3{}", name);
    }
}
