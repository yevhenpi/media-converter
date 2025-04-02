package ua.pidopryhora.mediaconverter.auth.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ua.pidopryhora.mediaconverter.auth.repository.TestApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.main.allow-bean-definition-overriding=true",
                "eureka.client.register-with-eureka=false",
                "eureka.client.fetch-registry=false"})
class SecuredControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void notLoggedInAccessRestricted() throws Exception {
        api.perform(get("/auth/test"))
                .andExpect(status().is(403));
    }
    @Test
    @WithMockUser
    void loggedInAccessAllowed() throws Exception {
        api.perform(get("/auth/test"))
                .andExpect(status().is(200));
    }

}