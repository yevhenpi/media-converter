package ua.pidopryhora.mediaconverter.core.s3;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import ua.pidopryhora.mediaconverter.common.aws.AwsProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class S3Downloader {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    public S3Downloader(S3Client s3Client, AwsProperties awsProperties) {
        this.s3Client = s3Client;
        this.awsProperties = awsProperties;
    }

    public String download(String key, Path filePath) {
        log.debug("Downloading file with key {} from S3 to {}", key, filePath);
        createDir(filePath);
        try {
            String bucketName = awsProperties.getUploadBucketName();

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
        return filePath.toString();
    }

    public void createDir(Path path){
        Path parent = path.getParent();
        if(!Files.exists(parent)){
            try {
                Files.createDirectories(parent);
            } catch (IOException e) {
                log.error("CANT CREATE DIR");
            }
        }
    }
}
