package ua.pidopryhora.mediaconverter.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobStatusDto {

    private String jobId;
    private String jobStatus;
}
