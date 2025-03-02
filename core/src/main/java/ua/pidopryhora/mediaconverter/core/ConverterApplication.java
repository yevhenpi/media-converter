package ua.pidopryhora.mediaconverter.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.pidopryhora.mediaconverter.core",
        "ua.pidopryhora.mediaconverter.common"
})
@EnableJpaRepositories(basePackages = {
        "ua.pidopryhora.mediaconverter.core",
        "ua.pidopryhora.mediaconverter.common"
})
@EntityScan(basePackages = {
        "ua.pidopryhora.mediaconverter.common.data",
        "ua.pidopryhora.mediaconverter.core"})
public class ConverterApplication {
    public static void main(String[] args) {

        SpringApplication.run(ConverterApplication.class, args);



    }
}