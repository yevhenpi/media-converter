package ua.pidopryhora.mediaconverter.common.jave2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
@Slf4j
@Component
public class FormatSupplier {

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
}
