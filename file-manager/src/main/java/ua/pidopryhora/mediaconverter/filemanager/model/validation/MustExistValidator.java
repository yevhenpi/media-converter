package ua.pidopryhora.mediaconverter.filemanager.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ua.pidopryhora.mediaconverter.filemanager.service.FileDataService;
@AllArgsConstructor
public class MustExistValidator implements ConstraintValidator<MustExist, String> {

    private final FileDataService fileDataService;
    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        return fileDataService.isPresent(input);
    }
}
