package ua.pidopryhora.mediaconverter.filemanager.model;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.AudioFormatValidation;

@Setter
@Getter
@ToString
@FileSizeValidation
@IdempotencyCheck(message = "URL for this file is already created and not expired yet", groups = AdvancedCheck.class)
@GroupSequence({BasicCheck.class, AdvancedCheck.class, UploadRequestDTO.class})
public class UploadRequestDTO extends RequestDTO{
    //TODO: Create GenericFormatValidation annotation

    //Switch to GenericFormatValidation to enable video upload

    @NotBlank(message = "File name is required",groups = BasicCheck.class)
    @AudioFormatValidation(groups = BasicCheck.class)
    @MustNotExist(groups = BasicCheck.class)
    private String fileName;

    @Min(value = 1, message = "File size must be greater than zero",groups = BasicCheck.class)
    private long fileSize;

    private String role;

    private long userId;

}
