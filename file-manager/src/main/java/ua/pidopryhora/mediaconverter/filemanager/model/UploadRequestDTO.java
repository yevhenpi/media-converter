package ua.pidopryhora.mediaconverter.filemanager.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.common.model.RequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.FileMustNotExist;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.FileSizeValidation;
import ua.pidopryhora.mediaconverter.common.model.validation.FormatValidation;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.IdempotencyCheck;

@Setter
@Getter
@ToString
@FileSizeValidation
@IdempotencyCheck(message = "URL for this file is already created and not expired yet")
public class UploadRequestDTO extends RequestDTO{

    @NotBlank(message = "File name is required")
    @FormatValidation
    @FileMustNotExist
    private String fileName;

    @Min(value = 1, message = "File size must be greater than zero")
    private long fileSize;

    private String role;

    private long userId;

}
