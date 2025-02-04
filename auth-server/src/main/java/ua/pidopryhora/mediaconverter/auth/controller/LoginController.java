package ua.pidopryhora.mediaconverter.auth.controller;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pidopryhora.mediaconverter.auth.model.LoginRequest;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponse;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@NoArgsConstructor
public class LoginController {

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){

        return LoginResponse
                .builder()
                .accessToken("DEMO")
                .build();
    }


}
