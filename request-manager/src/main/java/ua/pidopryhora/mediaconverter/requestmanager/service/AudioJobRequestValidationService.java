package ua.pidopryhora.mediaconverter.requestmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.validation.FileExistenceValidator;
import ua.pidopryhora.mediaconverter.requestmanager.validation.IdempotencyValidator;
import ua.pidopryhora.mediaconverter.requestmanager.validation.JobFormatValidator;

@Service
@RequiredArgsConstructor
public class AudioJobRequestValidationService implements RequestValidationService<AudioJobRequestDTO> {

    private final IdempotencyValidator<AudioJobRequestDTO> idempotencyValidator;
    private final FileExistenceValidator<AudioJobRequestDTO> fileExistenceValidator;
    private final JobFormatValidator<AudioJobRequestDTO> jobFormatValidator;



    @Override

    public void validateRequest(AudioJobRequestDTO request) {
        jobFormatValidator.validate(request);

        fileExistenceValidator.validate(request);

        idempotencyValidator.validate(request);

    }
}
