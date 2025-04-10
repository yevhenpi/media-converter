package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq.RabbitUpdateProducer;

import java.util.Map;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;

@Service
@AllArgsConstructor
@Validated
public class AudioJobRequestProcessor implements RequestProcessor<AudioJobRequestDTO> {

    private final RabbitUpdateProducer updateProducer;
    private final JobDataService jobDataService;
    private final JobFactory<AudioJobRequestDTO, AudioJobDTO> jobFactory;
    private final RequestValidationService<AudioJobRequestDTO> uploadRequestValidationService;

    @Override
    public ResponseEntity<?> processRequest(@Valid AudioJobRequestDTO requestDTO){

        uploadRequestValidationService.validateRequest(requestDTO);

        var job = jobFactory.createJob(requestDTO);

        updateProducer.produce(AUDIO_CONVERSION_QUEUE, job);
        jobDataService.saveData(job);

        return ResponseEntity.ok().body(Map.of("message", "processing is started",
                "jobId", job.getJobId()));
    }
}
