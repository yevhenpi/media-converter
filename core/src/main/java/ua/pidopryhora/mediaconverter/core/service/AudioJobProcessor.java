package ua.pidopryhora.mediaconverter.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.core.core.AudioAttributesBuilder;
import ua.pidopryhora.mediaconverter.core.core.AudioConverter;


@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class AudioJobProcessor implements JobProcessor<AudioJobDTO> {

    private final AudioConverter converter;
    private final AudioAttributesBuilder attributesBuilder;


    @Override
    @Async
    public void processJob(AudioJobDTO jobDTO) {
        log.debug("converting audio");
        var filePath = "path";
        var attributes = attributesBuilder.buildEncodingAttributes(jobDTO);

        converter.convertAudio(attributes, filePath);

    }
}
