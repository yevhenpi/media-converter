package ua.pidopryhora.mediaconverter.requestmanager.model.validation;

import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;

public interface RequestValidator<T> {
    boolean validate(T request) throws ValidationException;
}
