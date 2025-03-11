package ua.pidopryhora.mediaconverter.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


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