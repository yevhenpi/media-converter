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

        String hash = hashUtil.getHash(requestDTO);


        updateProducer.produce(AUDIO_CONVERSION_QUEUE, createJob(requestDTO));

        return ResponseEntity.ok().body(Map.of("message", "conversion is started",
                "hash", hashUtil.getHash(requestDTO)));
    }

    private AudioJobDTO createJob(AudioConversionRequestDTO requestDTO){
        return AudioJobDTO.builder()
                .fileName(requestDTO.getFileName())
                .bitRate(requestDTO.getBitRate())
                .codec(requestDTO.getCodec())
                .channels(requestDTO.getChannels())
                .quality(requestDTO.getQuality())
                .samplingRate(requestDTO.getSamplingRate())
                .volume(requestDTO.getVolume())
                .userId(requestDTO.getUserId())
                .requestHash(hashUtil.getHash(requestDTO))
                .build();
    }
}
