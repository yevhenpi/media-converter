package ua.pidopryhora.mediaconverter.requestmanager.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class RequestDTO implements Serializable {

    //TODO:Figure out field inheritance compatibility with builder pattern

   // protected String fileName;
    protected long userId;
    protected String role;
}
