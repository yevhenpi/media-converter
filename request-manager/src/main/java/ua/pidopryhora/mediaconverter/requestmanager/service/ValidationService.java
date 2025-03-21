package ua.pidopryhora.mediaconverter.requestmanager.service;

import lombok.Setter;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.IdempotencyValidator;
@Service
public class ValidationService<T extends RequestDTO> {

    private final IdempotencyValidator<T> idempotencyValidator;

    public ValidationService(IdempotencyValidator<T> idempotencyValidator) {
        this.idempotencyValidator = idempotencyValidator;
    }

    void validate(T request){
        idempotencyValidator.validate(request);

    }
}
