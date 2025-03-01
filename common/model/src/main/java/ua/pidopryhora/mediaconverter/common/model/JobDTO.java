package ua.pidopryhora.mediaconverter.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Builder
@Getter
@Setter
public class JobDTO implements Serializable {

    public String fileName;
    public long userId;
}
