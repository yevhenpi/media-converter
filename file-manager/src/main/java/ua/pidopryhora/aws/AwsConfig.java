package ua.pidopryhora.aws;

import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;


@Configuration
public class AwsConfig {
    @Value("${aws.access.key}")
    String accessKey;
    @Value("${aws.secret.key}")
    String secretKey;
    @Value("${aws.region}")
    String regionName;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
    }

    @Bean
    public Region awsRegion() {
        return Region.of(regionName);
    }



    @Bean
    public S3Client s3Client(AwsCredentialsProvider credentialsProvider, Region region) {
        return S3Client
                .builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public SqsClient sqsClient(AwsCredentialsProvider credentialsProvider, Region region){
        return  SqsClient
                .builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

    }
}
