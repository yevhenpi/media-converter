package ua.pidopryhora.mediaconverter.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExceptionHandler {

    public Mono<Void> handleException(ServerWebExchange exchange, Throwable e){
        if (e instanceof com.auth0.jwt.exceptions.TokenExpiredException) {
            return sendErrorResponse(exchange, "Token expired", 401);
        } else if (e instanceof com.auth0.jwt.exceptions.JWTVerificationException) {
            return sendErrorResponse(exchange, "Invalid token", 401);
        } else if (e instanceof RefreshTokenNotAllowedException) {
            return sendErrorResponse(exchange, "Refresh token is not allowed", 401);
        } else {
            log.debug(e.getMessage());
            return sendErrorResponse(exchange, "Internal server error", 500);
        }

    }

    private Mono<Void> sendErrorResponse(ServerWebExchange exchange, String message, int statusCode) {
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.valueOf(statusCode));
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");

        String errorJson = String.format("{\"error\": \"%s\"}", message);
        byte[] bytes = errorJson.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        return exchange.getResponse().writeWith(
                Mono.just(exchange.getResponse().bufferFactory().wrap(bytes))
        );
    }
}
