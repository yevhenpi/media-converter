package ua.pidopryhora.mediaconverter.common.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
public class AudioJobDTO extends JobDTO {


    private String codec;
    private Integer bitRate;
    private Integer channels;
    private Integer samplingRate;
    private Integer volume;



}
