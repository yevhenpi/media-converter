package ua.pidopryhora.mediaconverter.requestmanager.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ua.pidopryhora.mediaconverter.requestmanager.exception.FileAlreadyUploadedException;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.FileDataService;

@AllArgsConstructor
public class FileMustNotExistValidator implements ConstraintValidator<MustNotExist, RequestDTO> {

    private final FileDataService fileDataService;
    @Override
    public boolean isValid(RequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (fileDataService.isPresent(requestDTO.getFileName(), requestDTO.getUserId()))  throw new FileAlreadyUploadedException("File is already uploaded");

        return true;
    }
}
