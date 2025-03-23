package ua.pidopryhora.mediaconverter.core.s3;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import ua.pidopryhora.mediaconverter.common.aws.AwsProperties;
import ua.pidopryhora.mediaconverter.core.service.DirectoryCreator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class S3Downloader {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;
    private final DirectoryCreator directoryCreator;

    public S3Downloader(S3Client s3Client, AwsProperties awsProperties, DirectoryCreator directoryCreator) {
        this.s3Client = s3Client;
        this.awsProperties = awsProperties;
        this.directoryCreator = directoryCreator;
    }
    //TODO: Needs investigation why two parameters needed in this method input. Looks unnecessary.

    public Path download(String key, Path filePath) {
        log.debug("Downloading file with key {} from S3 to {}", key, filePath);
        directoryCreator.createDirectory(filePath.getParent());
        try {
            String bucketName = awsProperties.getUploadBucketName();
            log.debug("Bucket name {}", bucketName);

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest);
            Files.copy(s3Object, filePath, StandardCopyOption.REPLACE_EXISTING);

            log.debug("File {} downloaded successfully to {}", key, filePath.toAbsolutePath());
        } catch (IOException e) {
            log.error("ERROR DOWNLOADING FILE FROM S3", e);

        }
        return filePath;
    }

}
