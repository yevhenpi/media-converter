package ua.pidopryhora.mediaconverter.requestmanager.service.s3;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "url.expiration-time")
@Data
public class UrlExpirationProperties {
    private long upload;
    private long download;
}