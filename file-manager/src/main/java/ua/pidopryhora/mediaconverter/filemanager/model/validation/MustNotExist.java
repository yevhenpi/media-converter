package ua.pidopryhora.mediaconverter.filemanager.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileMustNotExistValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MustNotExist {
    String message() default "File is already uploaded";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
