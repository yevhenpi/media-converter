package ua.pidopryhora.mediaconverter.core.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import ua.pidopryhora.mediaconverter.common.aws.AwsProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
@Service
public class S3Uploader {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    public S3Uploader(S3Client s3Client, AwsProperties awsProperties){
        this.s3Client = s3Client;
        this.awsProperties = awsProperties;
    }


    public boolean upload(Path filePath){
        log.debug("File {} is being uploaded to s3", filePath.getFileName());
        try {
            byte[] fileContent = Files.readAllBytes(filePath);
            String bucketName = awsProperties.getCoreBucketName();
            String name = filePath.getParent().getFileName().toString()+"/"+filePath.getFileName().toString();



            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(name)
                            .build(),
                    RequestBody.fromBytes(fileContent)

            );

            log.debug("File {} is uploaded to s3", name);
            return true;
        } catch (IOException e) {
            log.error("ERROR LOADING FILE TO S3", e);
            return false;
        }

    }
}
