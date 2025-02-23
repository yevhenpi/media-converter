package ua.pidopryhora.mediaconverter.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ua.pidopryhora.mediaconverter.common.jave2.JAVEDataSupplier;
@RequiredArgsConstructor
public class FormatValidator implements ConstraintValidator<FormatValidation, String> {

    private final JAVEDataSupplier javeDataSupplier;

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        var fileFormat = extractFormatFromName(input);
        String[] formats = javeDataSupplier.getAudioFormats();
        boolean isPresent = false;
        for (String s:formats){
            if (s.equals(fileFormat)) {
                isPresent = true;
                break;
            }
        }

        return isPresent;

    }

    private String extractFormatFromName(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
