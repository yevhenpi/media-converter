package ua.pidopryhora.mediaconverter.auth.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    Optional<UserData> findByEmail(String email);
}
