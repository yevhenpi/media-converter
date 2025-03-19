package ua.pidopryhora.mediaconverter.requestmanager.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = MustExistValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MustExist {
    String message() default "File is not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
