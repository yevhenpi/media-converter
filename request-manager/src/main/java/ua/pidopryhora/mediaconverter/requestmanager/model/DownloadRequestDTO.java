package ua.pidopryhora.mediaconverter.requestmanager.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;

@Getter
@Setter
public class DownloadRequestDTO extends AuthenticatedRequestDTO {

    @NotBlank(message = "Job id is required")
    String jobId;



}
