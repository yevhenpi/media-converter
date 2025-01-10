package ua.pidopryhora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ua.pidopryhora.model.MediaMessage;
@Slf4j
@Component
public class UploadProcessor {


    @Async
    public void process(MediaMessage mediaMessage) throws InterruptedException {

        log.info("File is being processed");
        Thread.sleep(10000);

    }
}
