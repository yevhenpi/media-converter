package ua.pidopryhora.mediaconverter.requestmanager.validation;

import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;

public interface Validator<T> {
    void validate(T request) throws ValidationException;
}
