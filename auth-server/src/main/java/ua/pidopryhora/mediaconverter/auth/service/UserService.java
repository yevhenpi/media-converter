package ua.pidopryhora.mediaconverter.auth.service;

import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.auth.entity.UserEntity;

import java.util.Optional;

@Service
public class UserService {

    private static final String EMAIL = "test@gmail.com";

    public Optional<UserEntity> findByEmail(String email){
        //TODO implement database

        if(!EMAIL.equalsIgnoreCase(email)) return Optional.empty();

        var user = new UserEntity();
        user.setId(1L);
        user.setEmail(EMAIL);
        user.setPassword("$2a$12$RQPhUTkI21/fTGBL9.zkF.toGujjZ/RxAtvjwKv97itRYjhy6gsU.");
        user.setRole("ADMIN");
        return Optional.of(user);


    }


}
