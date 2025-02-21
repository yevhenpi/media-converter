package ua.pidopryhora.mediaconverter.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AudioRequestDTO extends RequestDTO {


    @NotBlank(message = "File name is required")
    private String fileName;
    @NotBlank(message = "Output format is required")
    private String outputFormat;

    private String codec = "libmp3lame";
    private String samplingRate = null;
    private String bitRate = null;
    private String channels = null;
    private String volume = null;
    private String quality = null;

    private String role;
    private long userId;



}
