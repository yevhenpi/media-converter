package ua.pidopryhora.service.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import ua.pidopryhora.config.AwsProperties;

import java.net.URL;
import java.time.Duration;
@Slf4j
@Service
public class S3PresignedUrlService {

    private final S3Presigner presigner;
    private final AwsProperties awsProperties;

    public S3PresignedUrlService(S3Presigner presigner,
                                 AwsProperties awsProperties){
        this.presigner = presigner;
        this.awsProperties = awsProperties;
    }

    public URL generatePresignedUrl(String fileName, long expirationMinutes) {
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
                    .signatureDuration(Duration.ofMinutes(expirationMinutes))
                    .build();
        } catch (Exception e) {
            log.error("Cannot create presigned url ", e);
        }

        log.debug("Presigned url ready for file {} ", fileName);

        return presigner.presignPutObject(presignRequest).url();
    }
}
