package ua.pidopryhora.mediaconverter.auth.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pidopryhora.mediaconverter.auth.model.LoginRequest;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponse;
import ua.pidopryhora.mediaconverter.auth.security.JwtIssuer;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final JwtIssuer jwtIssuer;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));

        return LoginResponse
                .builder()
                .accessToken(token)
                .build();
    }


}
