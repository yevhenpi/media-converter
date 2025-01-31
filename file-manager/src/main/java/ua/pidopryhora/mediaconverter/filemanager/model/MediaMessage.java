package ua.pidopryhora.mediaconverter.filemanager.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MediaMessage {

    private MultipartFile file;
    private String format;

    public  MediaMessage(MultipartFile file, String format){
        this.file = file;
        this.format = format;
    }
}
