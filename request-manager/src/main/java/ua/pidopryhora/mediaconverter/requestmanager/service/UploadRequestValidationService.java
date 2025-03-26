package ua.pidopryhora.mediaconverter.requestmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.validation.FileAbsenceValidator;
import ua.pidopryhora.mediaconverter.requestmanager.validation.FileSizeValidator;
import ua.pidopryhora.mediaconverter.requestmanager.validation.IdempotencyValidator;
import ua.pidopryhora.mediaconverter.requestmanager.validation.UploadFormatValidator;

@Service
@RequiredArgsConstructor
public class UploadRequestValidationService implements RequestValidationService<UploadRequestDTO> {

    private final IdempotencyValidator<UploadRequestDTO> idempotencyValidator;
    private final FileSizeValidator fileSizeValidator;
    private final FileAbsenceValidator fileAbsenceValidator;
    private final UploadFormatValidator formatValidator;



    public void validate(UploadRequestDTO request){
        formatValidator.validate(request);
        fileAbsenceValidator.validate(request);
        fileSizeValidator.validate(request);
        idempotencyValidator.validate(request);

    }
}
