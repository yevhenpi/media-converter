package ua.pidopryhora.mediaconverter.core.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class AudioConverter {

    public boolean convert(EncodingAttributes attributes, String inputPath, String outputPath) {
        boolean succeeded = true;

        try {
            File source = new File(inputPath);
            File target = new File(outputPath);

            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attributes);
        } catch (Exception e){
            log.error("FILE CONVERSION FAILED", e);
            succeeded = false;

        }

        return succeeded;

    }
}
