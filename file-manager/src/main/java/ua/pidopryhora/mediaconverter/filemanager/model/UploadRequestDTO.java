package ua.pidopryhora.mediaconverter.filemanager.model;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.common.model.RequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.*;
import ua.pidopryhora.mediaconverter.common.model.validation.FormatValidation;

@Setter
@Getter
@ToString
@FileSizeValidation
@IdempotencyCheck(message = "URL for this file is already created and not expired yet", groups = AdvancedCheck.class)
@GroupSequence({BasicCheck.class, AdvancedCheck.class, UploadRequestDTO.class})
public class UploadRequestDTO extends RequestDTO{

    @NotBlank(message = "File name is required",groups = BasicCheck.class)
    @FormatValidation
    @FileMustNotExist(groups = BasicCheck.class)
    private String fileName;

    @Min(value = 1, message = "File size must be greater than zero",groups = BasicCheck.class)
    private long fileSize;

    private String role;

    private long userId;

}
