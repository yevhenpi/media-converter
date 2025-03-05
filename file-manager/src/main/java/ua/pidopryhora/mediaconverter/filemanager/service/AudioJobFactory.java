package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.AudioConversionRequestDTO;

@Component
public class AudioJobFactory {

    public AudioJobDTO createJob(AudioConversionRequestDTO requestDTO, String jobId) {
        AudioJobDTO.AudioJobDTOBuilder builder = AudioJobDTO.builder()
                .fileName(requestDTO.getFileName())
                .outputFormat(requestDTO.getOutputFormat())
                .codec(requestDTO.getCodec())
                .userId(requestDTO.getUserId())
                .jobId(jobId)
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
}
