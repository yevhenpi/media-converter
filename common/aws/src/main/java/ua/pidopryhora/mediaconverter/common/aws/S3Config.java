package ua.pidopryhora.mediaconverter.common.aws;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;


@Configuration
public class S3Config {

    private final AwsCredentialsProvider credentialsProvider;
    private final Region region;

    public S3Config(AwsCredentialsProvider credentialsProvider, Region region) {
        this.credentialsProvider = credentialsProvider;
        this.region = region;
    }

    @Bean
    public S3Presigner s3Presigner(){
        return S3Presigner
                .builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client
                .builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
    }

}
