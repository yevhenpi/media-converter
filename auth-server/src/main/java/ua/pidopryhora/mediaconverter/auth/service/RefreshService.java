package ua.pidopryhora.mediaconverter.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.model.RefreshResponseDTO;
@Slf4j
@Service
public class RefreshService {

    public RefreshResponseDTO processRefreshToken(String refreshToken){

        log.debug(refreshToken);

        return RefreshResponseDTO.builder()
                .accessToken(refreshToken)
                .build();


    }
}
