package ua.pidopryhora.mediaconverter.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class ConversionRequestDTO {


    @NotBlank(message = "File name is required")
    private String fileName;
    @NotBlank(message = "Target format is required")
    private String targetFormat;

    private String targetResolution;
    private String targetFrameRate;

    private String role;
    private long userId;
    private String requestId;


}
