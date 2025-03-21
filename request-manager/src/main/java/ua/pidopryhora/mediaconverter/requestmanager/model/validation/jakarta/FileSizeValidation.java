package ua.pidopryhora.mediaconverter.requestmanager.model.validation.jakarta;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileSizeValidator.class)
@Target({ElementType.TYPE})  // Validate the whole class, not just one field
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSizeValidation {
    String message() default "Invalid file size for the given role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
