package ua.pidopryhora.mediaconverter.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshResponseDTO {

    private String accessToken;
}
