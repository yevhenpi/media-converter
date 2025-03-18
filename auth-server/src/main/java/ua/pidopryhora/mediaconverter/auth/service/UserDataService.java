package ua.pidopryhora.mediaconverter.auth.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.repository.UserDataRepository;
import ua.pidopryhora.mediaconverter.auth.entity.UserData;

import java.util.Optional;

@Slf4j
@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    public  UserDataService(UserDataRepository userDataRepository,
                                   PasswordEncoder passwordEncoder){
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
        addTestUsers();

    }

    public UserData createUser(String email, String rawPassword) {
        UserData user = new UserData();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER");

        return userDataRepository.save(user);
    }
    public UserData createUser(String email, String rawPassword, String role) {
        UserData user = new UserData();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);

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

    public void addTestUsers(){
        if(userDataRepository.findAll().isEmpty()){
            createUser("user@gmail.com","test");
            createUser("admin@gmail.com","test", "ADMIN");
        }
    }


}
