package ua.pidopryhora.mediaconverter.filemanager.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ua.pidopryhora.mediaconverter.filemanager.service.FileDataService;

@AllArgsConstructor
public class FileMustNotExistValidator implements ConstraintValidator<MustNotExist, String> {
    //TODO: Deal with file name uniqueness. Right now cant upload duplicates.

    private final FileDataService fileDataService;
    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        return !fileDataService.isPresent(input);
    }
}
