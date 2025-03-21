package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioConversionRequestDTO;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq.UpdateProducer;

import java.util.Map;
import java.util.UUID;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;

@Service
@AllArgsConstructor
@Validated
public class AudioConversionRequestProcessor implements RequestProcessor<AudioConversionRequestDTO> {

    private final UpdateProducer updateProducer;
    private final JobDataService jobDataService;
    private final JobFactory<AudioConversionRequestDTO, AudioJobDTO> jobFactory;
    private final ValidationService<AudioConversionRequestDTO> validationService;

    public ResponseEntity<?> processRequest(@Valid AudioConversionRequestDTO requestDTO){

        validationService.validate(requestDTO);

        var jobId = UUID.randomUUID().toString();
        var job = jobFactory.createJob(requestDTO, jobId);

        updateProducer.produce(AUDIO_CONVERSION_QUEUE, job);
        jobDataService.saveData(job);

        return ResponseEntity.ok().body(Map.of("message", "conversion is started",
                "jobId", jobId));
    }
}
