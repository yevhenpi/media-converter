package ua.pidopryhora.mediaconverter.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class AudioJobDTO extends JobDTO {

    private String fileName;
    private String outputFormat;
    private String codec;
    private int samplingRate;
    private int bitRate;
    private int channels;
    private int volume;


    private long userId;
    private String jobId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getS3Key(){ return  userId +fileName;}
}
