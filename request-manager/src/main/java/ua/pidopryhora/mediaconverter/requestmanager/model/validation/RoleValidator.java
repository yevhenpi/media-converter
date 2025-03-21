package ua.pidopryhora.mediaconverter.requestmanager.model.validation;

import jakarta.validation.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;

public class RoleValidator<T extends RequestDTO> implements RequestValidator<T>{

    @Override
    public void validate(T request) throws ValidationException {

    }
}
