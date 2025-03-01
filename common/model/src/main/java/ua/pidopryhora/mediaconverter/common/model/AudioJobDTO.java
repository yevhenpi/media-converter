package ua.pidopryhora.mediaconverter.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Builder
@Getter
@Setter
public class AudioJobDTO implements Serializable {

    private String fileName;
    private String outputFormat;
    private String codec;
    private String samplingRate;
    private String bitRate;
    private String channels;
    private String volume;
    private String quality;
    private long userId;
}
