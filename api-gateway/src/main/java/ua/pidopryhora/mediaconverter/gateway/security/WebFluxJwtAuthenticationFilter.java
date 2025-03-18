package ua.pidopryhora.mediaconverter.gateway.security;

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
@Slf4j
@Component
@RequiredArgsConstructor
public class WebFluxJwtAuthenticationFilter implements WebFilter {

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
                    .map(jwtToPrincipalConverter::convert)
                    .flatMap(principal -> {
                        // Mutate the request: remove the original token and add new headers with user info.
                        ServerWebExchange mutatedExchange = getMutatedExchange(exchange, principal);
                        // Optionally, create an authentication token for the reactive security context.
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

    private static ServerWebExchange getMutatedExchange(ServerWebExchange exchange, UserPrincipal principal) {
        return exchange.mutate()
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
    }


}
