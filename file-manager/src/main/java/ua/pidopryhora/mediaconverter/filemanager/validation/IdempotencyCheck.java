package ua.pidopryhora.mediaconverter.filemanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdempotencyValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdempotencyCheck {
    String message() default "Duplicated request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
