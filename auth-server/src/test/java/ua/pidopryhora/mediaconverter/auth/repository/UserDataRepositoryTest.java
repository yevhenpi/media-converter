//package ua.pidopryhora.mediaconverter.auth.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.ContextConfiguration;
//import ua.pidopryhora.mediaconverter.auth.controller.LoginController;
//import ua.pidopryhora.mediaconverter.auth.entity.UserData;
//import ua.pidopryhora.mediaconverter.auth.security.CustomUserDetailService;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//@Slf4j
//@DataJpaTest
//@ContextConfiguration(classes = TestApplication.class)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//class UserDataRepositoryTest {
//
//    @Autowired
//    UserDataRepository testRepository;
//    @Autowired
//    private EntityManager entityManager;
//
//    @AfterEach
//    void tearDown(){
//        testRepository.deleteAll();
//    }
//
//    @Test
//    void shouldFindByEmail() {
//        UserData userData = new UserData();
//        userData.setEmail("fake@gmail.com");
//        userData.setPassword("password");
//        userData.setRole("USER");
//        userData.setStatus("OK");
//        userData = testRepository.save(userData);
//
//        // When
//        testRepository.save(userData);
//        entityManager.clear(); // Clear the persistence context to ensure fresh data is loaded
//
//        // Then
//        Optional<UserData> found = testRepository.findByEmail("fake@gmail.com");
//        UserData finalUserData = userData;
//        assertThat(found)
//                .isPresent()
//                .hasValueSatisfying(u -> assertThat(u.getId()).isEqualTo(finalUserData.getId()));
//    }
//
//
//    @Test
//    public void shouldUpdateRefreshToken() {
//        // Create and save a new user
//        UserData user = new UserData();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//        user.setRole("USER");
//        user.setStatus("OK");
//        user = testRepository.save(user);
//
//        // Perform a bulk update
//        int updatedRows = testRepository.updateRefreshToken("new-token", user.getId());
//        assertEquals(1, updatedRows);
//
//        // Clear the persistence context to force a reload from the DB
//        entityManager.clear();
//
//        // Fetch the updated user
//        UserData updatedUser = testRepository.findById(user.getId())
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//        assertEquals("new-token", updatedUser.getRefreshToken());
//    }
//
//
//
//}