package ua.pidopryhora.mediaconverter.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.security.JwtProperties;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
@RequiredArgsConstructor
@Component
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    public String issueAccessToken(long userId, String email, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("e", email)
                .withClaim("a",roles)
                .withClaim("type", "ACCESS")
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }

    public String issueRefreshToken(long userId, String email, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(60, ChronoUnit.DAYS)))
                .withClaim("e", email)
                .withClaim("a",roles)
                .withClaim("type","REFRESH")
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }

}
