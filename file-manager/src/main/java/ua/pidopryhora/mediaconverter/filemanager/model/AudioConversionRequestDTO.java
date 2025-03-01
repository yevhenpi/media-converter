package ua.pidopryhora.mediaconverter.filemanager.model;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.*;

@Setter
@Getter
@ToString
@IdempotencyCheck(message = "Request is already being processed", groups = AdvancedCheck.class)
@GroupSequence({BasicCheck.class, AdvancedCheck.class, AudioConversionRequestDTO.class})
public class AudioConversionRequestDTO extends RequestDTO {


    @NotBlank(message = "File name is required",groups = BasicCheck.class)
    @MustExist
    private String fileName;
    @NotBlank(message = "Output format is required", groups = BasicCheck.class)
    @AudioFormatValidation(groups = BasicCheck.class)
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
