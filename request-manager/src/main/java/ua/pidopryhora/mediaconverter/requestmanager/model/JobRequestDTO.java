package ua.pidopryhora.mediaconverter.requestmanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class JobRequestDTO extends RequestDTO{

    public String fileName;

}
