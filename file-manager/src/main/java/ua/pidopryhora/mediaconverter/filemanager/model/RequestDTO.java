package ua.pidopryhora.mediaconverter.filemanager.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class RequestDTO implements Serializable {

    protected String fileName;
    protected long userId;
    protected String role;
}
