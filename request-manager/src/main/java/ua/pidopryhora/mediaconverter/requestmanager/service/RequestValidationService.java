package ua.pidopryhora.mediaconverter.requestmanager.service;

import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;

public interface RequestValidationService<T extends AuthenticatedRequestDTO> {
    void validateRequest(T request);
}
