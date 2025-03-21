package ua.pidopryhora.mediaconverter.requestmanager.service.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import ua.pidopryhora.mediaconverter.common.aws.AwsProperties;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;

import java.net.URL;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3PresignedUrlService implements PresignedUrlService {

    private final S3Presigner presigner;
    private final AwsProperties awsProperties;
    private final JobDataService jobDataService;
    private final UrlExpirationProperties expirationProperties;



    public URL generatePresignedUrl(UploadRequestDTO metadata) {
        PutObjectPresignRequest presignRequest = null;
        try {
            // Create a PutObjectRequest
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getUploadBucketName())
                    .key(metadata.getS3Key())
                    .contentLength(metadata.getFileSize())
                    .build();

            // Generate the Presigned URL
            presignRequest = PutObjectPresignRequest.builder()
                    .putObjectRequest(objectRequest)
                    .signatureDuration(Duration.ofMinutes(expirationProperties.getUpload()))
                    .build();
        } catch (Exception e) {
            log.error("Cannot create presigned url ", e);
        }

        log.debug("Presigned url ready for file {} ", metadata.getFileName());

        return presigner.presignPutObject(presignRequest).url();
    }

    public URL generatePresignedUrl(String jobId){
        String key = jobDataService.getJob(jobId).getS3Key();
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(awsProperties.getCoreBucketName())
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(expirationProperties.getDownload()))
                .getObjectRequest(getObjectRequest)
                .build();

        return presigner.presignGetObject(presignRequest).url();

    }

}
