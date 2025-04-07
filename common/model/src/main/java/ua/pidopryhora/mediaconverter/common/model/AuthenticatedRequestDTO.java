package ua.pidopryhora.mediaconverter.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AuthenticatedRequestDTO implements Serializable {

    //TODO:Figure out field inheritance compatibility with builder pattern

   // protected String fileName;
    protected long userId;
    protected String role;
}
