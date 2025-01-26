package ua.pidopryhora.service.s3;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import ua.pidopryhora.config.AwsProperties;

import java.net.URL;
import java.time.Duration;

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
        // Create a PutObjectRequest
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(awsProperties.getUploadBucketName())
                .key(fileName)
                .build();

        // Generate the Presigned URL
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .putObjectRequest(objectRequest)
                .signatureDuration(Duration.ofMinutes(expirationMinutes))
                .build();

        return presigner.presignPutObject(presignRequest).url();
    }
}
