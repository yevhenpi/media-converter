package ua.pidopryhora.mediaconverter.requestmanager.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DownloadRequestDTO extends RequestDTO {


    @NotBlank(message = "Job id is required")
    String jobId;

    String role;
    long userId;


}
