package ua.pidopryhora.mediaconverter.filemanager.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.filemanager.model.AudioRequestDTO;
import ua.pidopryhora.mediaconverter.common.model.JobDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.rabbitmq.UpdateProducer;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.util.Map;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.CONVERSION_QUEUE;

@Service
@AllArgsConstructor
@Validated
public class ConversionRequestProcessor {
    //TODO:Add target format validation.

    private final UpdateProducer updateProducer;
    private final HashUtil hashUtil;
    private final FileDataService fileDataService;


    public ResponseEntity<?> process(@Valid AudioRequestDTO requestDTO){


        if (!fileDataService.isPresent(requestDTO.getFileName())) return ResponseEntity.badRequest().body(Map.of("error", "file is not found"));

        updateProducer.produce(CONVERSION_QUEUE, createJob(requestDTO));

        return ResponseEntity.ok().body(Map.of("message", "conversion is started",
                "hash", hashUtil.getHash(requestDTO)));
    }

    private JobDTO createJob(AudioRequestDTO requestDTO){
        return JobDTO.builder()
                .fileName(requestDTO.getFileName())
                .userId(requestDTO.getUserId())
                .build();
    }
}
