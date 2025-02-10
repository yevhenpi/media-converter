package ua.pidopryhora.mediaconverter.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FileManagerApplication {
    //TODO: Implement idempotency

    public static void main(String[] args){
        SpringApplication.run(FileManagerApplication.class,args );
    }

}
