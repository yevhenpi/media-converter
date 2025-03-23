package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;

import java.util.UUID;

@Component
public class AudioJobFactory implements JobFactory<AudioJobRequestDTO, AudioJobDTO> {

    public AudioJobDTO createJob(AudioJobRequestDTO requestDTO) {
        var builder = AudioJobDTO.builder()
                .fileName(requestDTO.getFileName())
                .outputFormat(requestDTO.getOutputFormat())
                .codec(requestDTO.getCodec())
                .userId(requestDTO.getUserId())
                .jobId(createUuid())
                .s3Key(requestDTO.getS3Key());

        if (isNotBlank(requestDTO.getBitRate())) {
            builder.bitRate(Integer.parseInt(requestDTO.getBitRate()));
        }
        if (isNotBlank(requestDTO.getChannels())) {
            builder.channels(Integer.parseInt(requestDTO.getChannels()));
        }
        if (isNotBlank(requestDTO.getSamplingRate())) {
            builder.samplingRate(Integer.parseInt(requestDTO.getSamplingRate()));
        }
        if (isNotBlank(requestDTO.getVolume())) {
            builder.volume(Integer.parseInt(requestDTO.getVolume()));
        }
        return builder.build();
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private String createUuid(){
      return UUID.randomUUID().toString();
    }
}
