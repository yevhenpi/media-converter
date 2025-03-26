package ua.pidopryhora.mediaconverter.requestmanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DownloadRequestDTO extends RequestDTO {
    //TODO:Add basic validation



    String jobId;

    String role;
    long userId;


}
