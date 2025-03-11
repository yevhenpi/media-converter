package ua.pidopryhora.mediaconverter.filemanager.model;

import lombok.Getter;
import lombok.Setter;
import ua.pidopryhora.mediaconverter.filemanager.model.validation.JobMustExist;

@Getter
@Setter
public class DownloadRequestDTO {

    @JobMustExist
    String jobId;
}
