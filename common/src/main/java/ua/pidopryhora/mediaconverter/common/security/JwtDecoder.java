package ua.pidopryhora.mediaconverter.common.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256("secret"))
                .build()
                .verify(token);
    }
}
