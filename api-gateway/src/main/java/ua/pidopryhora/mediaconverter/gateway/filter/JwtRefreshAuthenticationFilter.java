package ua.pidopryhora.mediaconverter.gateway.filter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import ua.pidopryhora.mediaconverter.gateway.exception.AccessTokenNotAllowedException;
import ua.pidopryhora.mediaconverter.gateway.exception.ExceptionHandler;

import static ua.pidopryhora.mediaconverter.common.model.UserRole.GUEST;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRefreshAuthenticationFilter implements WebFilter {

    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;
    private final ExceptionHandler exceptionHandler;

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange,@NonNull WebFilterChain chain) {
        String token = extractTokenFromRequest(exchange);
        if (token != null) {
            return Mono.just(token)
                    .map(jwtDecoder::decode)
                    .flatMap(jwt -> {
                        String tokenType = jwt.getClaim("type").asString();
                        if ("ACCESS".equalsIgnoreCase(tokenType)) {
                            return Mono.error(new AccessTokenNotAllowedException("Access token is not allowed for refresh."));
                        }
                        return Mono.just(jwt);
                    })
                    .map(jwtToPrincipalConverter::convert)
                    .flatMap(principal -> {
                        // Mutate the request: remove the original token and add new headers with user info.
                        ServerWebExchange mutatedExchange = getMutatedExchange(exchange, principal, token);
                        // Create an authentication token for the reactive security context.
                        UserPrincipalAuthenticationToken authentication = new UserPrincipalAuthenticationToken(principal);
                        return chain.filter(mutatedExchange)
                                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
                    })
                    .onErrorResume(e -> exceptionHandler.handleException(exchange,e));
        }
        // If no token is present, continue as usual.
        return chain.filter(exchange);
    }

    private String extractTokenFromRequest(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    private static ServerWebExchange getMutatedExchange(ServerWebExchange exchange, UserPrincipal principal, String token) {
        return exchange.mutate()
                .request(req -> req.headers(httpHeaders -> {
                    // Remove the Authorization header.
                    httpHeaders.remove(HttpHeaders.AUTHORIZATION);
                    String authority = principal.getAuthorities()
                            .stream()
                            .findFirst()
                            .map(GrantedAuthority::getAuthority)
                            .orElse(String.valueOf(GUEST));
                    // Add new headers with data extracted from the token.
                    httpHeaders.add("RefreshToken", token);
                    httpHeaders.add("Email", principal.getEmail());
                    httpHeaders.add("UserId", String.valueOf(principal.getUserId()));
                    httpHeaders.add("UserRole", authority);
                }))
                .build();
    }


}
