package ua.pidopryhora.mediaconverter.requestmanager.validation;

import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;

public interface Validator<T> {
    void validate(T request) throws ValidationException;
}
