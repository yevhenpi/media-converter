package ua.pidopryhora.mediaconverter.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponse;
import ua.pidopryhora.mediaconverter.auth.security.JwtIssuer;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipal;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String email, String password){

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        var token = jwtIssuer.issueAccessToken(principal.getUserId(), principal.getEmail(), roles);

        return LoginResponse
                .builder()
                .accessToken(token)
                .build();

    }
}
