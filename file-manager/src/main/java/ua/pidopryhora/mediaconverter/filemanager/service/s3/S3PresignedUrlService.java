package ua.pidopryhora.mediaconverter.filemanager.service.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import ua.pidopryhora.mediaconverter.filemanager.config.AwsProperties;

import java.net.URL;
import java.time.Duration;
@Slf4j
@Service
@RequiredArgsConstructor
public class S3PresignedUrlService {
    //TODO: Add fileSize to link creation

    private final S3Presigner presigner;
    private final AwsProperties awsProperties;
    //TODO: move to properties
    private final long EXPIRATION_TIME = 10L;

    public URL generatePresignedUrl(String fileName) {
        PutObjectPresignRequest presignRequest = null;
        try {
            // Create a PutObjectRequest
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getUploadBucketName())
                    .key(fileName)
                    .build();

            // Generate the Presigned URL
            presignRequest = PutObjectPresignRequest.builder()
                    .putObjectRequest(objectRequest)
                    .signatureDuration(Duration.ofMinutes(EXPIRATION_TIME))
                    .build();
        } catch (Exception e) {
            log.error("Cannot create presigned url ", e);
        }

        log.debug("Presigned url ready for file {} ", fileName);

        return presigner.presignPutObject(presignRequest).url();
    }
}
