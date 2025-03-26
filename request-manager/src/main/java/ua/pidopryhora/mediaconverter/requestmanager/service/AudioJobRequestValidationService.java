package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.FileExistenceValidator;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.IdempotencyValidator;
@Service
public class AudioJobRequestValidationService implements RequestValidationService<AudioJobRequestDTO> {

    private final IdempotencyValidator<AudioJobRequestDTO> idempotencyValidator;
    private final FileExistenceValidator<AudioJobRequestDTO> fileExistenceValidator;

    public AudioJobRequestValidationService(IdempotencyValidator<AudioJobRequestDTO> idempotencyValidator, FileExistenceValidator<AudioJobRequestDTO> fIleExistenceValidator) {
        this.idempotencyValidator = idempotencyValidator;
        this.fileExistenceValidator = fIleExistenceValidator;
    }

    @Override

    public void validate(AudioJobRequestDTO request) {
        fileExistenceValidator.validate(request);

        idempotencyValidator.validate(request);

    }
}
