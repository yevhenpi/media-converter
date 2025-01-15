package ua.pidopryhora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.pidopryhora.aws.S3Uploader;
import ua.pidopryhora.model.MediaMessage;

@Slf4j
@Component
public class UploadProcessor {

    private final S3Uploader s3Uploader;
    private final LocalStorage localStorage;

    public UploadProcessor(S3Uploader s3Uploader,
                           LocalStorage localStorage){

        this.s3Uploader = s3Uploader;
        this.localStorage = localStorage;
    }



    public void process(MediaMessage mediaMessage){

        log.info("File is being processed");
        var FILE_URI = localStorage.saveFile(mediaMessage.getFile());
        s3Uploader.upload(FILE_URI).thenAccept(success -> {
            if (success) {
                localStorage.deleteFile(FILE_URI);
            } else {
                log.debug("NO FILE TO DELETE");
            }
        });
    }
}
