package ua.pidopryhora.mediaconverter.filemanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3Uploader;
import ua.pidopryhora.mediaconverter.filemanager.model.MediaMessage;

@Slf4j
@Component
public class UploadProcessor {

    private final S3Uploader s3Uploader;
    private final TempStorage tempStorage;

    public UploadProcessor(S3Uploader s3Uploader,
                           TempStorage tempStorage){

        this.s3Uploader = s3Uploader;
        this.tempStorage = tempStorage;
    }



    public void process(MediaMessage mediaMessage){

        log.info("File is being processed");
        var FILE_URI = tempStorage.saveFile(mediaMessage.getFile());
        s3Uploader.upload(FILE_URI).thenAccept(success -> {
            if (success) {
                tempStorage.deleteFile(FILE_URI);
            } else {
                log.info("NO FILE TO DELETE");
            }
        });
    }
}
