package ua.pidopryhora.mediaconverter.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.pidopryhora.mediaconverter.gateway",
        "ua.pidopryhora.mediaconverter.common"  // Include common module package
})
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);


    }
}