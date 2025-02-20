package ua.pidopryhora.mediaconverter.common.jave2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;

@Slf4j
@Component
public class JAVEDataSupplier {

    private final String[] def = {};

    private final Encoder encoder = new Encoder();

    public String[] getEncodingFormats()  {

        try {
            return encoder.getSupportedEncodingFormats();
        } catch (EncoderException e) {
            log.error("CANT GET ENCODING FORMATS", e);
        }
        return def;
    }

    public String[] getDecodingFormats() {

        try {
            return encoder.getSupportedDecodingFormats();
        } catch (EncoderException e) {
            log.error("CANT GET DECODING FORMATS", e);
        }
        return def;
    }

    public String[] getAudioEncoders(){

        try {
            return encoder.getAudioEncoders();
        } catch (EncoderException e) {
            log.error("CANT GET AUDIO ENCODERS", e);
        }
        return def;
    }

    public String[] getAudioDecoders(){

        try {
            return encoder.getAudioDecoders();
        } catch (EncoderException e) {
            log.error("CANT GET AUDIO DECODERS", e);
        }
        return def;
    }

    public String[] getVideoEncoders(){

        try {
            return encoder.getVideoEncoders();
        } catch (EncoderException e) {
            log.error("CANT GET VIDEO ENCODERS", e);
        }
        return def;
    }

    public String[] getVideoDecoders(){

        try {
            return encoder.getVideoDecoders();
        } catch (EncoderException e) {
            log.error("CANT GET VIDEO DECODERS", e);
        }
        return def;
    }
    
    public String[] getAudioFormats(){

        return new String[]{"mp3","aac","wav","flac", "ogg", "opus", "dts", "ac3"};
    }
}
