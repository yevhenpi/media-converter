package ua.pidopryhora.mediaconverter.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pidopryhora.mediaconverter.auth.model.LoginRequest;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponse;
import ua.pidopryhora.mediaconverter.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {

        return authService.attemptLogin(request.getEmail(), request.getPassword());

    }


}
