package ua.pidopryhora.mediaconverter.filemanager.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.filemanager.model.AudioConversionRequestDTO;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.rabbitmq.UpdateProducer;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.util.Map;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;

@Service
@AllArgsConstructor
@Validated
public class AudioConversionRequestProcessor implements RequestProcessor<AudioConversionRequestDTO> {
    //TODO:Add target format validation.

    private final UpdateProducer updateProducer;
    private final HashUtil hashUtil;



    public ResponseEntity<?> processRequest(@Valid AudioConversionRequestDTO requestDTO){

        updateProducer.produce(AUDIO_CONVERSION_QUEUE, createJob(requestDTO));

        return ResponseEntity.ok().body(Map.of("message", "conversion is started",
                "hash", hashUtil.getHash(requestDTO)));
    }

    private AudioJobDTO createJob(AudioConversionRequestDTO requestDTO) {

        AudioJobDTO.AudioJobDTOBuilder builder = AudioJobDTO.builder()
                .fileName(requestDTO.getFileName())
                .outputFormat(requestDTO.getOutputFormat())
                .codec(requestDTO.getCodec())      // Assuming codec is optional, may be null
                .userId(requestDTO.getUserId())
                .requestHash(hashUtil.getHash(requestDTO));

        if (requestDTO.getBitRate() != null && !requestDTO.getBitRate().trim().isEmpty()) {
            builder.bitRate(Integer.parseInt(requestDTO.getBitRate()));
        }
        if (requestDTO.getChannels() != null && !requestDTO.getChannels().trim().isEmpty()) {
            builder.channels(Integer.parseInt(requestDTO.getChannels()));
        }
        if (requestDTO.getSamplingRate() != null && !requestDTO.getSamplingRate().trim().isEmpty()) {
            builder.samplingRate(Integer.parseInt(requestDTO.getSamplingRate()));
        }
        if (requestDTO.getVolume() != null && !requestDTO.getVolume().trim().isEmpty()) {
            builder.volume(Integer.parseInt(requestDTO.getVolume()));
        }

        return builder.build();
    }

}
