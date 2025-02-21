package ua.pidopryhora.mediaconverter.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RequestDTO {

    protected String fileName;
    protected long userId;
    protected String role;
}
