package ua.pidopryhora.mediaconverter.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.auth.service.UserDataService;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipal;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private  final UserDataService userService;
    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username).orElseThrow();

        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
                .password(user.getPassword())
                .build();
    }
}
