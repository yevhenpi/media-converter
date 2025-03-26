package ua.pidopryhora.mediaconverter.requestmanager.service;

import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;

public interface RequestValidationService<T extends RequestDTO> {
    void validateRequest(T request);
}
