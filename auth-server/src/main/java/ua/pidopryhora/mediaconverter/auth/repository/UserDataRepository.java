package ua.pidopryhora.mediaconverter.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pidopryhora.mediaconverter.auth.entity.UserData;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    Optional<UserData> findByEmail(String email);
}
