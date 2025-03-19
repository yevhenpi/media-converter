package ua.pidopryhora.mediaconverter.requestmanager.model;

import lombok.Getter;
import lombok.Setter;
import ua.pidopryhora.mediaconverter.requestmanager.model.validation.JobMustExist;

@Getter
@Setter
public class DownloadRequestDTO {

    @JobMustExist
    String jobId;
}
