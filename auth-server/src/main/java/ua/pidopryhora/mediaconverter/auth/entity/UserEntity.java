package ua.pidopryhora.mediaconverter.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UserEntity {

    private Long id;

    private String email;
    @JsonIgnore
    private String password;

    private String role;

}
