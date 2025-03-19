package ua.pidopryhora.mediaconverter.requestmanager.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AudioFormatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AudioFormatValidation {
    String message() default "Format is not supported";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
