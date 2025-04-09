package ua.pidopryhora.mediaconverter.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.pidopryhora.mediaconverter.auth",
        "ua.pidopryhora.mediaconverter.common"
})
public class AuthenticationServerApplication {


    public static void main(String[] args){
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }
}
