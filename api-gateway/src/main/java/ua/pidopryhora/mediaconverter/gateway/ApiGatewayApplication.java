package ua.pidopryhora.mediaconverter.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
//TODO: Eureka url is not overridden by docker compose when imported as config.
// It works only when eureka config written down in main application.yaml

@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.pidopryhora.mediaconverter.gateway",
        "ua.pidopryhora.mediaconverter.common.security",
        "ua.pidopryhora.mediaconverter.common.cache"

})
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);


    }
}