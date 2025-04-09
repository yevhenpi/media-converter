package ua.pidopryhora.mediaconverter.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.model.LoginResponseDTO;
import ua.pidopryhora.mediaconverter.auth.security.JwtIssuer;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipal;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final UserDataService userDataService;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDTO attemptLogin(String email, String password){

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        var accessToken = jwtIssuer.issueAccessToken(principal.getUserId(), principal.getEmail(), roles);

        var refreshToken = jwtIssuer.issueRefreshToken(principal.getUserId(), principal.getEmail(),roles);

        userDataService.updateRefreshToken(refreshToken, principal.getUserId());

        return LoginResponseDTO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
}
