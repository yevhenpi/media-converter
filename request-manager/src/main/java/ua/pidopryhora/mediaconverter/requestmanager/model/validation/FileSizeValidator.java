package ua.pidopryhora.mediaconverter.requestmanager.model.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;

import java.util.Map;

public class FileSizeValidator implements ConstraintValidator<FileSizeValidation, UploadRequestDTO> {

    private static final Map<String, Long> MAX_FILE_SIZES = Map.of(
            "ADMIN", 100_000_000L, // 100MB for admins
            "PREMIUM", 10_000_000L,   // 10MB for regular users
            "USER", 1_000_000L    // 1MB for guests
    );

    @Override
    public boolean isValid(UploadRequestDTO uploadRequest, ConstraintValidatorContext context) {
        if (uploadRequest == null || uploadRequest.getRole() == null) {
            return false; // Invalid if role is missing
        }

        long maxSize = MAX_FILE_SIZES.getOrDefault(uploadRequest.getRole().toUpperCase(), 5_000_000L); // Default 5MB
        return uploadRequest.getFileSize() <= maxSize;
    }
}
