package ua.pidopryhora.mediaconverter.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.core.core.AudioAttributesBuilder;
import ua.pidopryhora.mediaconverter.core.core.AudioConverter;

import java.nio.file.Path;


@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class AudioJobProcessor implements JobProcessor<AudioJobDTO> {

    private final AudioConverter converter;
    private final AudioAttributesBuilder attributesBuilder;
    private final FileManager fileManager;

    @Override
    @Async
    public void processJob(AudioJobDTO jobDTO) {

        var filePath = fileManager.downloadFile(jobDTO.getS3Key());

        var attributes = attributesBuilder.buildEncodingAttributes(jobDTO);

        var targetPath = fileManager.getTargetPath(jobDTO.getS3Key(), jobDTO.getOutputFormat());

        if(converter.convert(attributes, filePath, targetPath)){
            fileManager.uploadFile(Path.of(targetPath));
        }



    }



}
