package ua.pidopryhora.mediaconverter.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.entity.UserDataRepository;
import ua.pidopryhora.mediaconverter.auth.entity.UserEntity;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<UserEntity> findByEmail(String email){

         return userDataRepository.findByEmail(email);
    }


}
