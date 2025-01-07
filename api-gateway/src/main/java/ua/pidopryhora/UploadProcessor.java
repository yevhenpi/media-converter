package ua.pidopryhora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
public class UploadProcessor {



    public UploadProcessor(){

    }


    @Async
    public void process(String queueName, MultipartFile file, String format) throws InterruptedException {

        log.info("File is uploaded");

    }
}
