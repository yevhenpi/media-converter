package ua.pidopryhora.mediaconverter.requestmanager.model.validation;

import jakarta.validation.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;

public class IdempotencyValidator<T extends RequestDTO> implements RequestValidator<T>{


    @Override
    public boolean validate(T request) throws ValidationException {
        return false;
    }
}
