package ua.pidopryhora.mediaconverter.requestmanager.validation.jakarta;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = JobMustExistValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JobMustExist {
    String message() default "Job is not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
