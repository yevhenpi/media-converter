package ua.pidopryhora.mediaconverter.auth.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pidopryhora.mediaconverter.auth.model.LoginRequest;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponse;
import ua.pidopryhora.mediaconverter.auth.security.JwtIssuer;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipal;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();
        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);

        return LoginResponse
                .builder()
                .accessToken(token)
                .build();
    }


}
