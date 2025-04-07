package ua.pidopryhora.mediaconverter.requestmanager.model;

import lombok.Getter;
import lombok.Setter;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;

@Getter
@Setter
public abstract class JobRequestDTO extends AuthenticatedRequestDTO {

    public String fileName;
    public String outputFormat;

}
