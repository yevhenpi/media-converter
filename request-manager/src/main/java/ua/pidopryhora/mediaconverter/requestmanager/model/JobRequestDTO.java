package ua.pidopryhora.mediaconverter.requestmanager.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;

@Getter
@Setter
public abstract class JobRequestDTO extends AuthenticatedRequestDTO {


    @NotBlank(message = "File name is required")
    private String fileName;
    @NotBlank(message = "Output format is required")
    private String outputFormat;

}
