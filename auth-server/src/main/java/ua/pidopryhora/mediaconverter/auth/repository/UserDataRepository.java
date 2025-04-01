package ua.pidopryhora.mediaconverter.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.pidopryhora.mediaconverter.auth.entity.UserData;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    Optional<UserData> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE UserData u SET u.refreshToken = :token WHERE u.id = :id")
    int updateRefreshToken(@Param("token") String token, @Param("id") Long id);
}
