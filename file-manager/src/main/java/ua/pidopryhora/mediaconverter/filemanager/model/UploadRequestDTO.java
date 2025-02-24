package ua.pidopryhora.mediaconverter.filemanager.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.common.model.RequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.validation.FileSizeValidation;
import ua.pidopryhora.mediaconverter.common.validation.FormatValidation;

import java.io.Serializable;

@Setter
@Getter
@ToString
//@FileSizeValidation
public class UploadRequestDTO extends RequestDTO implements Serializable {

    @NotBlank(message = "File name is required")
    @FormatValidation
    private String fileName;
    @Min(value = 1, message = "File size must be greater than zero")
    private long fileSize;

    private String role;
    private long userId;

}
