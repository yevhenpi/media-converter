package ua.pidopryhora.mediaconverter.common.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
public class AudioJobDTO extends JobDTO {

    private String fileName;
    private String outputFormat;
    private String codec;
    private int bitRate;
    private int channels;
    private int samplingRate;
    private int volume;


    private String s3Key;
    private long userId;
    private String jobId;


}
