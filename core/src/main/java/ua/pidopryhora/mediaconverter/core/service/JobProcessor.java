package ua.pidopryhora.mediaconverter.core.service;

import ua.pidopryhora.mediaconverter.common.model.JobDTO;

public interface JobProcessor<T extends JobDTO> {
    void processJob(T jobDTO);
}
