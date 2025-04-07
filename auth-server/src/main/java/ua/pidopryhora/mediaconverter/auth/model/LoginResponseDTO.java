package ua.pidopryhora.mediaconverter.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {

    private final String accessToken;
    private final String refreshToken;
}
