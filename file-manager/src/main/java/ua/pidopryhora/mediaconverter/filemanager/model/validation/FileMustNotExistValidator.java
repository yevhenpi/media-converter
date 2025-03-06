package ua.pidopryhora.mediaconverter.filemanager.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.FileDataService;

@AllArgsConstructor
public class FileMustNotExistValidator implements ConstraintValidator<MustNotExist, RequestDTO> {

    private final FileDataService fileDataService;
    @Override
    public boolean isValid(RequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        return !fileDataService.isPresent(requestDTO.getFileName(), requestDTO.getUserId());
    }
}
