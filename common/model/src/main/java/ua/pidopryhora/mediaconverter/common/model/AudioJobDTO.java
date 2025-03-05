package ua.pidopryhora.mediaconverter.common.model;


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

    private String s3Key;
    private long userId;
    private String jobId;


}
