package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq.UpdateProducer;

import java.util.Map;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;

@Service
@AllArgsConstructor
@Validated
public class AudioJobRequestProcessor implements RequestProcessor<AudioJobRequestDTO> {

    private final UpdateProducer updateProducer;
    private final JobDataService jobDataService;
    private final JobFactory<AudioJobRequestDTO, AudioJobDTO> jobFactory;
    private final UploadValidationService<AudioJobRequestDTO> uploadValidationService;

    public ResponseEntity<?> processRequest(@Valid AudioJobRequestDTO requestDTO){

        uploadValidationService.validate(requestDTO);

        var job = jobFactory.createJob(requestDTO);

        updateProducer.produce(AUDIO_CONVERSION_QUEUE, job);
        jobDataService.saveData(job);

        return ResponseEntity.ok().body(Map.of("message", "conversion is started",
                "jobId", job.getJobId()));
    }
}
