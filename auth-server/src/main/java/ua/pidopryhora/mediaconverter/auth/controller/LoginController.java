package ua.pidopryhora.mediaconverter.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pidopryhora.mediaconverter.auth.model.LoginRequest;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponse;
import ua.pidopryhora.mediaconverter.auth.model.RefreshRequest;
import ua.pidopryhora.mediaconverter.auth.model.RefreshResponse;
import ua.pidopryhora.mediaconverter.auth.service.AuthService;
import ua.pidopryhora.mediaconverter.auth.service.RefreshService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class LoginController {

    private final AuthService authService;
    private final RefreshService refreshService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {

        return authService.attemptLogin(request.getEmail(), request.getPassword());

    }

    @PostMapping("/refresh")
    private RefreshResponse refresh(@RequestBody @Valid RefreshRequest request){

        return null;


    }


}
