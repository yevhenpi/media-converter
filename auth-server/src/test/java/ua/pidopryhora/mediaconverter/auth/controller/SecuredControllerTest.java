package ua.pidopryhora.mediaconverter.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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