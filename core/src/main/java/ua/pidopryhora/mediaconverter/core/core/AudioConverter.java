package ua.pidopryhora.mediaconverter.core.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class AudioConverter {

    private final Encoder encoder;

    public AudioConverter() {
        this.encoder = new Encoder();
    }

    public boolean convert(EncodingAttributes attributes, Path inputPath, Path outputPath) {
        if (!validateInput(inputPath)) {
            return false;
        }

        try {
            encoder.encode(new MultimediaObject(inputPath.toFile()), outputPath.toFile(), attributes);
            return true;
        } catch (IllegalArgumentException e) {
            log.error("Invalid encoding attributes: {}", attributes, e);
        } catch (Exception e) {
            log.error("File conversion failed for {} -> {}", inputPath, outputPath, e);
        }

        return false;
    }

    private boolean validateInput(Path inputPath) {
        if (!Files.exists(inputPath)) {
            log.error("Input file does not exist: {}", inputPath);
            return false;
        }
        if (!Files.isReadable(inputPath)) {
            log.error("Input file is not readable: {}", inputPath);
            return false;
        }
        return true;
    }
}
