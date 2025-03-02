package ua.pidopryhora.mediaconverter.common.jave2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class JAVEDataSupplier {

    private final Encoder encoder;

    private List<String> encodingFormats;
    private List<String> decodingFormats;
    private List<String> audioEncoders;
    private List<String> audioDecoders;
    private List<String> videoEncoders;
    private List<String> videoDecoders;

    private final List<String> supportedAudioFormats = Arrays.asList("mp3", "wav", "flac", "ogg", "opus", "ac3");

    public JAVEDataSupplier() {
        this.encoder = new Encoder();
        loadSupportedFormats();
    }

    /**
     * Loads supported formats on application startup.
     */
    private void loadSupportedFormats() {
        try {
            encodingFormats = Arrays.asList(encoder.getSupportedEncodingFormats());
            decodingFormats = Arrays.asList(encoder.getSupportedDecodingFormats());
            audioEncoders = Arrays.asList(encoder.getAudioEncoders());
            audioDecoders = Arrays.asList(encoder.getAudioDecoders());
            videoEncoders = Arrays.asList(encoder.getVideoEncoders());
            videoDecoders = Arrays.asList(encoder.getVideoDecoders());
        } catch (EncoderException e) {
            log.error("Failed to load encoding/decoding formats", e);
            encodingFormats = decodingFormats = audioEncoders = audioDecoders = videoEncoders = videoDecoders = Collections.emptyList();
        }
    }

    public String[] getEncodingFormats() {
        return encodingFormats.toArray(new String[0]);
    }

    public String[] getDecodingFormats() {
        return decodingFormats.toArray(new String[0]);
    }

    public String[] getAudioEncoders() {
        return audioEncoders.toArray(new String[0]);
    }

    public String[] getAudioDecoders() {
        return audioDecoders.toArray(new String[0]);
    }

    public String[] getVideoEncoders() {
        return videoEncoders.toArray(new String[0]);
    }

    public String[] getVideoDecoders() {
        return videoDecoders.toArray(new String[0]);
    }

    public String[] getAudioFormats() {
        return supportedAudioFormats.toArray(new String[0]);
    }
}
