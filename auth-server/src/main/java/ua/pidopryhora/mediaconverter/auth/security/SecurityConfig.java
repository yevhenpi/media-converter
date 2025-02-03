package ua.pidopryhora.mediaconverter.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for stateless APIs
                .csrf(AbstractHttpConfigurer::disable)
                // Disable CORS (enable if needed)
                .cors(AbstractHttpConfigurer::disable)
                // Set session management to stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Disable form login
                .formLogin(AbstractHttpConfigurer::disable)
                // Disable HTTP basic authentication
                .httpBasic(AbstractHttpConfigurer::disable)
                // Disable logout functionality (optional)
                .logout(AbstractHttpConfigurer::disable)
                // Permit all requests (customize as needed)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated())
                ;

        return http.build();
    }
}
