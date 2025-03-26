package ua.pidopryhora.mediaconverter.requestmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.requestmanager.validation.jakarta.AudioFormatValidation;

@Setter
@Getter
@ToString
public class AudioJobRequestDTO extends JobRequestDTO {
    //TODO: Advanced validation would be great


    @NotBlank(message = "File name is required")
    private String fileName;
    @NotBlank(message = "Output format is required")
    @AudioFormatValidation
    private String outputFormat;

    private String codec = null;
    private String samplingRate = null;
    private String bitRate = null;
    private String channels = null;
    private String volume = null;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getS3Key(){ return  userId+"/"+fileName;}



}
