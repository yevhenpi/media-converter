package ua.pidopryhora.mediaconverter.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import ua.pidopryhora.mediaconverter.common.security.JwtDecoder;
import ua.pidopryhora.mediaconverter.common.security.JwtToPrincipalConverter;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipal;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipalAuthenticationToken;

@Component
@RequiredArgsConstructor
public class WebFluxJwtAuthenticationFilter implements WebFilter {

    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = extractTokenFromRequest(exchange);
        if (token != null) {
            return Mono.just(token)
                    .map(jwtDecoder::decode)
                    .map(jwtToPrincipalConverter::convert)
                    .flatMap(principal -> {
                        // Mutate the request: remove the original token and add new headers with user info.
                        ServerWebExchange mutatedExchange = getMutatedExchange(exchange, principal);
                        // Optionally, create an authentication token for the reactive security context.
                        UserPrincipalAuthenticationToken authentication = new UserPrincipalAuthenticationToken(principal);
                        return chain.filter(mutatedExchange)
                                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
                    })
                    .onErrorResume(e -> handleJwtException(exchange, e));
        }
        // If no token is present, continue as usual.
        return chain.filter(exchange);
    }

    private static ServerWebExchange getMutatedExchange(ServerWebExchange exchange, UserPrincipal principal) {
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(req -> req.headers(httpHeaders -> {
                    // Remove the Authorization header.
                    httpHeaders.remove(HttpHeaders.AUTHORIZATION);
                    String authority = principal.getAuthorities()
                            .stream()
                            .findFirst()  // get the first element as Optional<GrantedAuthority>
                            .map(GrantedAuthority::getAuthority)
                            .orElse("USER");
                    // Add new headers with data extracted from the token.
                    httpHeaders.add("UserId", String.valueOf(principal.getUserId()));
                    httpHeaders.add("UserRole", authority);
                }))
                .build();
        return mutatedExchange;
    }

    private String extractTokenFromRequest(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
    private Mono<Void> handleJwtException(ServerWebExchange exchange, Throwable e) {
        if (e instanceof com.auth0.jwt.exceptions.TokenExpiredException) {
            return sendErrorResponse(exchange, "Token expired", 401);
        } else if (e instanceof com.auth0.jwt.exceptions.JWTVerificationException) {
            return sendErrorResponse(exchange, "Invalid token", 401);
        } else {
            return sendErrorResponse(exchange, "Authentication error", 401);
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
