package ua.pidopryhora.mediaconverter.common.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
@Getter
@Setter
public abstract class JobDTO implements Serializable {

    private String fileName;
    private String outputFormat;
    private long userId;
    private String jobId;
    private String s3Key;


}
