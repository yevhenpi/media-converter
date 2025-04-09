package ua.pidopryhora.mediaconverter.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
//@SuperBuilder(toBuilder = true)
public abstract class AuthenticatedRequestDTO implements Serializable {

    //TODO:Figure out field inheritance compatibility with builder pattern


    protected long userId;
    protected String role;
}
