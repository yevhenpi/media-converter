package ua.pidopryhora.mediaconverter.auth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    //@Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "Only alphanumeric characters are allowed")
    @NotBlank(message = "Email is required")
    private String email;

   // @Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "Only alphanumeric characters are allowed")
    @NotBlank(message = "Password is required")
    private String password;
}
