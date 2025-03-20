package ua.pidopryhora.mediaconverter.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.core.core.AudioAttributesBuilder;
import ua.pidopryhora.mediaconverter.core.core.AudioConverter;
import ws.schild.jave.encode.EncodingAttributes;

import java.nio.file.Path;

import static ua.pidopryhora.mediaconverter.common.model.JobStatus.DONE;
import static ua.pidopryhora.mediaconverter.common.model.JobStatus.FAILED;


@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class AudioJobProcessor implements JobProcessor<AudioJobDTO> {

    private final AudioConverter converter;
    private final AudioAttributesBuilder attributesBuilder;
    private final FileManager fileManager;
    private final JobDataService jobDataService;

    @Override
    @Async
    public void processJob(AudioJobDTO jobDTO) {

        Path filePath = fileManager.downloadFile(jobDTO.getS3Key());

        EncodingAttributes attributes = attributesBuilder.buildEncodingAttributes(jobDTO);

        Path targetPath = fileManager.getTargetPath(jobDTO.getJobId(), jobDTO.getOutputFormat());

        if(converter.convert(attributes, filePath, targetPath)){
            fileManager.uploadFile(targetPath);
            jobDataService.updateJobStatus(jobDTO.getJobId(), String.valueOf(DONE));
            jobDataService.updateS3Key(jobDTO.getJobId(), getKeyFromTarget(targetPath));
        } else {
            jobDataService.updateJobStatus(jobDTO.getJobId(), String.valueOf(FAILED));
        }

    }

    private String getKeyFromTarget(Path target){

        return target.getFileName().toString();
    }



}
