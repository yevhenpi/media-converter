package ua.pidopryhora.mediaconverter.filemanager.model;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class RequestMetadataDTO {


    @NotBlank(message = "File name is required")
    private String fileName;
    @Min(value = 1, message = "File size must be greater than zero")
    private long fileSize;
    @NotBlank(message = "Target format is required")
    private String targetFormat;

    private String targetResolution;
    private String targetFrameRate;

    private String role;
    private long userId;


}
