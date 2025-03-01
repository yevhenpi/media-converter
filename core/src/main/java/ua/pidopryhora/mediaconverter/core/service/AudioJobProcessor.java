package ua.pidopryhora.mediaconverter.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
@Slf4j
@Service
@EnableAsync
public class AudioJobProcessor implements JobProcessor<AudioJobDTO> {


    @Override
    @Async
    public void processJob(AudioJobDTO jobDTO) {
        log.debug("converting audio");

    }
}
