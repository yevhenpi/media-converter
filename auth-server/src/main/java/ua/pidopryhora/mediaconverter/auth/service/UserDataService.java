package ua.pidopryhora.mediaconverter.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.entity.UserDataRepository;
import ua.pidopryhora.mediaconverter.auth.entity.UserData;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    public UserData createUser(String email, String rawPassword) {
        UserData user = new UserData();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword)); // ✅ Hash password
        user.setRole("USER"); // ✅ Default role

        return userDataRepository.save(user);
    }


    public Optional<UserData> findByEmail(String email){

         return userDataRepository.findByEmail(email);
    }

    public void updateUserRole(Long userId, String newRole) {
        userDataRepository.findById(userId).ifPresent(user -> {
            user.setRole(newRole);
            userDataRepository.save(user);
        });
    }


}
