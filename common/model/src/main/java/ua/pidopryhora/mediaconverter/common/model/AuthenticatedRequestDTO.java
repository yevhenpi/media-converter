package ua.pidopryhora.mediaconverter.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
public abstract class AuthenticatedRequestDTO implements Serializable {

    protected long userId;
    protected String role;
}
