package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.FileSizeValidator;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.IdempotencyValidator;
@Service
public class UploadValidationService<T extends RequestDTO> {

    private final IdempotencyValidator<T> idempotencyValidator;
    private final FileSizeValidator fileSizeValidator;

    public UploadValidationService(IdempotencyValidator<T> idempotencyValidator, FileSizeValidator fileSizeValidator) {
        this.idempotencyValidator = idempotencyValidator;
        this.fileSizeValidator = fileSizeValidator;
    }

    void validate(T request){

        idempotencyValidator.validate(request);

    }
}
