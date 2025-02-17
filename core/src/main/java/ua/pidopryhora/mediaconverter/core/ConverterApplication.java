package ua.pidopryhora.mediaconverter.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.pidopryhora.mediaconverter.core",
        "ua.pidopryhora.mediaconverter.common"
})
public class ConverterApplication {
    public static void main(String[] args) {

        SpringApplication.run(ConverterApplication.class, args);



    }
}