package ua.pidopryhora.mediaconverter.requestmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AudioJobRequestDTO extends JobRequestDTO {
    //TODO: Advanced validation would be great


    @NotBlank(message = "File name is required")
    private String fileName;
    @NotBlank(message = "Output format is required")
    private String outputFormat;

    @Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "Only alphanumeric characters are allowed")
    private String codec = null;
    @Pattern(regexp = "^\\d+$", message = "Must contain only numbers")
    private String samplingRate = null;
    @Pattern(regexp = "^\\d+$", message = "Must contain only numbers")
    private String bitRate = null;
    @Pattern(regexp = "^\\d+$", message = "Must contain only numbers")
    private String channels = null;
    @Pattern(regexp = "^\\d+$", message = "Must contain only numbers")
    private String volume = null;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getS3Key(){ return  userId+"/"+fileName;}



}
