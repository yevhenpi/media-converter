package ua.pidopryhora.mediaconverter.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.CorsSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.LogoutSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import ua.pidopryhora.mediaconverter.gateway.filter.JwtAccessAuthenticationFilter;
import ua.pidopryhora.mediaconverter.gateway.filter.JwtRefreshAuthenticationFilter;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class WebFluxSecurityConfig {

    private final JwtAccessAuthenticationFilter jwtAuthenticationFilter;
    private final JwtRefreshAuthenticationFilter refreshAuthenticationFilter;

    @Bean
    @Order(2)
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                // Disable CSRF
                .csrf(CsrfSpec::disable)
                // Disable CORS
                .cors(CorsSpec::disable)
                // Disable form login
                .formLogin(FormLoginSpec::disable)
                // Disable basic auth
                .httpBasic(HttpBasicSpec::disable)
                // This ensures no session is created
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                // You can also disable logout if you don't need session-based logout
                .logout(LogoutSpec::disable)

                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/login").permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    @Order(1)  // Higher precedence than the default chain
    public SecurityWebFilterChain refreshSecurityWebFilterChain(ServerHttpSecurity http) {
        return http
                // Apply this chain only to the /auth/refresh endpoint
                .securityMatcher(ServerWebExchangeMatchers.pathMatchers("/auth/refresh/**"))
                // Disable features not needed for this endpoint
                .csrf(CsrfSpec::disable)
                .cors(CorsSpec::disable)
                .formLogin(FormLoginSpec::disable)
                .httpBasic(HttpBasicSpec::disable)
                .logout(LogoutSpec::disable)
                // Use a stateless security context
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                // Require authentication for any request matching /auth/refresh
                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                // Add your custom refresh token filter at the authentication stage
                .addFilterAt(refreshAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }



}
