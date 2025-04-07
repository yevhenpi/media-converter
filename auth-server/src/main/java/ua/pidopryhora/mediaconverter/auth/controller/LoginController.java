package ua.pidopryhora.mediaconverter.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.auth.model.LoginRequestDTO;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponseDTO;
import ua.pidopryhora.mediaconverter.auth.model.RefreshRequestDTO;
import ua.pidopryhora.mediaconverter.auth.model.RefreshResponseDTO;
import ua.pidopryhora.mediaconverter.auth.service.AuthService;
import ua.pidopryhora.mediaconverter.auth.service.RefreshService;
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class LoginController {

    private final AuthService authService;
    private final RefreshService refreshService;


    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO request) {

        return authService.attemptLogin(request.getEmail(), request.getPassword());

    }

    @GetMapping("/refresh")
    public RefreshResponseDTO refresh(@RequestHeader("UserId") String userId,
                                      @RequestHeader("UserRole") String userRole,
                                      @RequestHeader("RefreshToken") String refreshToken){




        return refreshService.processRefreshToken(refreshToken);


    }


}
