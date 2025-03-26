package ua.pidopryhora.mediaconverter.requestmanager.model;

import jakarta.validation.GroupSequence;
import lombok.Getter;
import lombok.Setter;
import ua.pidopryhora.mediaconverter.requestmanager.validation.jakarta.AdvancedCheck;
import ua.pidopryhora.mediaconverter.requestmanager.validation.jakarta.BasicCheck;
import ua.pidopryhora.mediaconverter.requestmanager.validation.jakarta.JobMustBeDone;
import ua.pidopryhora.mediaconverter.requestmanager.validation.jakarta.JobMustExist;

@Getter
@Setter
@GroupSequence({BasicCheck.class, AdvancedCheck.class, DownloadRequestDTO.class})
public class DownloadRequestDTO extends RequestDTO {

    @JobMustExist(groups = BasicCheck.class)
    @JobMustBeDone(groups = AdvancedCheck.class)
    String jobId;

    String role;
    long userId;


}
