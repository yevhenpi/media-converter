package ua.pidopryhora.mediaconverter.filemanager.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;

@Getter
public class RequestData {


    @NotBlank(message = "File name is required")
    private String fileName;
    @Min(value = 1, message = "File size must be greater than zero")
    private long fileSize;
    @NotBlank(message = "Target format is required")
    private String targetFormat;

}
