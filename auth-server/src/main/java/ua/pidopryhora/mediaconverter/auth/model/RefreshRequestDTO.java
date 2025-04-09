package ua.pidopryhora.mediaconverter.auth.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;
@Getter
@Setter
public class RefreshRequestDTO extends AuthenticatedRequestDTO {

    private String refreshToken;
    private String email;

}
