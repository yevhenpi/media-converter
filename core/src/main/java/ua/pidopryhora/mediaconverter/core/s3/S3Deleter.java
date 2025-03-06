package ua.pidopryhora.mediaconverter.core.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import ua.pidopryhora.mediaconverter.common.aws.AwsProperties;
import ua.pidopryhora.mediaconverter.common.data.JobData;

import java.util.List;

@Slf4j
@Service
public class S3Deleter {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    public S3Deleter(S3Client s3Client, AwsProperties awsProperties) {
        this.s3Client = s3Client;
        this.awsProperties = awsProperties;
    }

    public boolean delete(String key) {
        log.debug("Attempting to delete S3 object with key: {}", key);
        try {
            String bucketName = awsProperties.getCoreBucketName();
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
            log.debug("Successfully deleted S3 object with key: {}", key);
            return true;
        } catch (Exception e) {
            log.error("Error occurred while deleting S3 object with key: {}", key, e);
            return false;
        }
    }

    public void batchDelete(List<JobData> jobsToDelete){
        for(JobData job: jobsToDelete){
            delete(job.getS3Key());
        }
    }


}
