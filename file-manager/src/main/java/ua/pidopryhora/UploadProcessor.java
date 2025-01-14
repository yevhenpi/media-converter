package ua.pidopryhora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ua.pidopryhora.aws.S3Uploader;
import ua.pidopryhora.model.MediaMessage;
@Slf4j
@Component
public class UploadProcessor {

    private final S3Uploader s3Uploader;

    public UploadProcessor(S3Uploader s3Uploader){
        this.s3Uploader = s3Uploader;
    }


    //@Async
    public void process(MediaMessage mediaMessage) throws InterruptedException {

        log.info("File is being processed");
        s3Uploader.upload(mediaMessage.getFile());

    }
}
