package ua.pidopryhora.mediaconverter.requestmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.pidopryhora.mediaconverter.requestmanager",
        "ua.pidopryhora.mediaconverter.common"
})
@EnableJpaRepositories(basePackages = {
        "ua.pidopryhora.mediaconverter.requestmanager",
        "ua.pidopryhora.mediaconverter.common"
})
@EntityScan(basePackages = {
        "ua.pidopryhora.mediaconverter.common.data",
        "ua.pidopryhora.mediaconverter.requestmanager"})
public class RequestManagerApplication {


    public static void main(String[] args){
        SpringApplication.run(RequestManagerApplication.class,args );
    }

}
