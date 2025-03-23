package ua.pidopryhora.mediaconverter.requestmanager.service;

import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;

public interface ValidationService<T extends RequestDTO> {
    void validate(T request);
}
