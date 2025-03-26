package ua.pidopryhora.mediaconverter.requestmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.jakarta.*;

@Setter
@Getter
@ToString
//@MustNotExist(groups = BasicCheck.class)
@GroupSequence({BasicCheck.class, AdvancedCheck.class, UploadRequestDTO.class})
public class UploadRequestDTO extends RequestDTO{
    //TODO: Create GenericFormatValidation annotation.Complex validation IS TEMPORARY here, complex logic should be moved to service layer.

    //Switch to GenericFormatValidation to enable video upload

    @Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "Only alphanumeric characters are allowed", groups = BasicCheck.class)
    @NotBlank(message = "File name is required",groups = BasicCheck.class)
    @AudioFormatValidation(groups = BasicCheck.class)
    private String fileName;

    @Min(value = 1, message = "File size must be greater than zero",groups = BasicCheck.class)
    private long fileSize;

    private String role;

    private long userId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getS3Key(){ return  userId+"/"+fileName;}

}
