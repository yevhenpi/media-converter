package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.IdempotencyValidator;
@Service
public class AudioJobRequestValidationService implements RequestValidationService<AudioJobRequestDTO> {

    private final IdempotencyValidator<AudioJobRequestDTO> idempotencyValidator;

    public AudioJobRequestValidationService(IdempotencyValidator<AudioJobRequestDTO> idempotencyValidator) {
        this.idempotencyValidator = idempotencyValidator;
    }

    @Override
    public void validate(AudioJobRequestDTO request) {

        idempotencyValidator.validate(request);

    }
}
