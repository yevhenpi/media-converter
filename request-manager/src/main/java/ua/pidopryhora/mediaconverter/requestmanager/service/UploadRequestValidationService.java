package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.FileSizeValidator;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.IdempotencyValidator;
@Service
public class UploadRequestValidationService implements RequestValidationService<UploadRequestDTO> {

    private final IdempotencyValidator<UploadRequestDTO> idempotencyValidator;
    private final FileSizeValidator fileSizeValidator;

    public UploadRequestValidationService(IdempotencyValidator<UploadRequestDTO> idempotencyValidator, FileSizeValidator fileSizeValidator) {
        this.idempotencyValidator = idempotencyValidator;
        this.fileSizeValidator = fileSizeValidator;
    }

    public void validate(UploadRequestDTO request){
        fileSizeValidator.validate(request);
        idempotencyValidator.validate(request);

    }
}
