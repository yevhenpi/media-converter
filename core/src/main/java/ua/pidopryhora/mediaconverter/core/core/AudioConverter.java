package ua.pidopryhora.mediaconverter.core.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
@Slf4j
@Component
public class AudioConverter {

    public boolean convert(EncodingAttributes attributes, String filePath) {
        boolean succeeded = true;

        try {
            File source = new File(filePath);
            File target = new File("file path");
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attributes);
        } catch (Exception e){
            log.error("FILE CONVERSION FAILED", e);
            succeeded = false;
        }

        return succeeded;

    }
}
