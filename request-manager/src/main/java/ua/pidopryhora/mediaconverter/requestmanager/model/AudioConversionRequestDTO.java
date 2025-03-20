package ua.pidopryhora.mediaconverter.requestmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.*;

@Setter
@Getter
@ToString
@MustExist
@IdempotencyCheck(message = "Request is already being processed", groups = AdvancedCheck.class)
@GroupSequence({BasicCheck.class, AdvancedCheck.class, AudioConversionRequestDTO.class})
public class AudioConversionRequestDTO extends RequestDTO {
    //TODO: Advanced validation would be great


    @NotBlank(message = "File name is required",groups = BasicCheck.class)
    private String fileName;
    @NotBlank(message = "Output format is required", groups = BasicCheck.class)
    @AudioFormatValidation(groups = BasicCheck.class)
    private String outputFormat;

    private String codec = null;
    private String samplingRate = null;
    private String bitRate = null;
    private String channels = null;
    private String volume = null;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getS3Key(){ return  userId+"/"+fileName;}



}
