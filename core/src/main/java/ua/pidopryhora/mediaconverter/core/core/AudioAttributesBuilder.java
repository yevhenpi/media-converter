package ua.pidopryhora.mediaconverter.core.core;



import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

@Component
public class AudioAttributesBuilder {

    public  EncodingAttributes buildEncodingAttributes(AudioJobDTO dto) {

        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setOutputFormat(dto.getOutputFormat());


        AudioAttributes audioAttributes = new AudioAttributes();


        if (dto.getCodec() != null && !dto.getCodec().trim().isEmpty()) {
            audioAttributes.setCodec(dto.getCodec());
        }
        if (dto.getSamplingRate() != 0) {
            int samplingRate = dto.getSamplingRate();
            audioAttributes.setSamplingRate(samplingRate);
        }
        if (dto.getBitRate() != 0) {
            int bitRate = dto.getBitRate();
            audioAttributes.setBitRate(bitRate);
        }
        if (dto.getChannels() != 0) {

            int channels = dto.getChannels();
            audioAttributes.setChannels(channels);

        }

        if (dto.getVolume() != 0) {

            int volume = dto.getVolume();
            audioAttributes.setQuality(volume);

        }

        encodingAttributes.setAudioAttributes(audioAttributes);

        return encodingAttributes;
    }
}
