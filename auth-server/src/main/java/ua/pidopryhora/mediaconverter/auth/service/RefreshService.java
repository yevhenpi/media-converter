package ua.pidopryhora.mediaconverter.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.model.RefreshRequestDTO;
import ua.pidopryhora.mediaconverter.auth.model.RefreshResponseDTO;
import ua.pidopryhora.mediaconverter.auth.security.JwtIssuer;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshService {

    private final JwtIssuer issuer;

    public RefreshResponseDTO processRefreshToken(RefreshRequestDTO requestDTO){

        List<String> roles = List.of(requestDTO.getRole());

        var accessToken = issuer.issueAccessToken(requestDTO.getUserId(), requestDTO.getEmail(), roles);

        return RefreshResponseDTO.builder()
                .accessToken(accessToken)
                .build();


    }
}
