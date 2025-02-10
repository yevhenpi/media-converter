package ua.pidopryhora.mediaconverter.common.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = JwtProperties.class)
@TestPropertySource(properties = {
        "jwt.secret=3ee973bd66a793eca0b7261bd924836ff24db61f0acfb"
})
class JwtPropertiesTest {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void testSecretIsLoaded() {
        assertEquals("test-secret-key", jwtProperties.getSecret(), "JWT secret should be correctly loaded from properties");
    }

}