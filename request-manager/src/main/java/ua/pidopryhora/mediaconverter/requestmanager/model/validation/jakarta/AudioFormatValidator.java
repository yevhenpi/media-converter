package ua.pidopryhora.mediaconverter.requestmanager.model.validation.jakarta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ua.pidopryhora.mediaconverter.common.jave2.JAVEDataSupplier;

import java.util.Arrays;

@RequiredArgsConstructor
public class AudioFormatValidator implements ConstraintValidator<AudioFormatValidation, String> {

    private final JAVEDataSupplier javeDataSupplier;

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        var fileFormat = extractFormatFromName(input);
        return Arrays.asList(javeDataSupplier.getAudioFormats()).contains(fileFormat);
    }


    private String extractFormatFromName(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
