package ua.pidopryhora.mediaconverter.auth.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;
@Getter
@Builder
public class RefreshRequestDTO extends AuthenticatedRequestDTO {

   // @NotBlank(message = "Refresh token is required")
    private String refreshToken;
    //private String role;
   // private long userId;
}
