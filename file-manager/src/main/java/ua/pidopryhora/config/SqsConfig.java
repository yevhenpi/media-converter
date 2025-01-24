package ua.pidopryhora.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SqsConfig {

    private final AwsCredentialsProvider credentialsProvider;
    private final Region region;

    public SqsConfig(AwsCredentialsProvider credentialsProvider, Region region) {
        this.credentialsProvider = credentialsProvider;
        this.region = region;
    }

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
