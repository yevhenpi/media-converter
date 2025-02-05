package ua.pidopryhora.mediaconverter.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import ua.pidopryhora.mediaconverter.common.security.JwtDecoder;
import ua.pidopryhora.mediaconverter.common.security.JwtToPrincipalConverter;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipalAuthenticationToken;

@Component
@RequiredArgsConstructor
public class WebFluxJwtAuthenticationFilter implements WebFilter {

    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipleConverter;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = extractTokenFromRequest(exchange);
        if (token != null) {
            // Create a reactive chain to decode and convert the token
            return Mono.just(token)
                    .map(jwtDecoder::decode)
                    .map(jwtToPrincipleConverter::convert)
                    .map(UserPrincipalAuthenticationToken::new)
                    // Add the authentication to the reactive security context
                    .flatMap(authentication -> chain.filter(exchange)
                                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
                    );
        }
        // If no token is present, continue the chain as usual.
        return chain.filter(exchange);
    }

    private String extractTokenFromRequest(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
