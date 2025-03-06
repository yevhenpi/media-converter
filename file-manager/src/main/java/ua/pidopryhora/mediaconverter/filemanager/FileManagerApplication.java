package ua.pidopryhora.mediaconverter.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.pidopryhora.mediaconverter.filemanager",
        "ua.pidopryhora.mediaconverter.common"
})
@EnableJpaRepositories(basePackages = {
        "ua.pidopryhora.mediaconverter.filemanager",
        "ua.pidopryhora.mediaconverter.common"
})
@EntityScan(basePackages = {
        "ua.pidopryhora.mediaconverter.common.data",
        "ua.pidopryhora.mediaconverter.filemanager"})
public class FileManagerApplication {


    public static void main(String[] args){
        SpringApplication.run(FileManagerApplication.class,args );
    }

}
