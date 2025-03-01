package ua.pidopryhora.mediaconverter.filemanager.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FormatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatValidation {
    String message() default "Format is not supported";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
