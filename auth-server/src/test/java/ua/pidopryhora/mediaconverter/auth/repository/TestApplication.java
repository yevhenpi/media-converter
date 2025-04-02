package ua.pidopryhora.mediaconverter.auth.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import ua.pidopryhora.mediaconverter.auth.controller.SecuredController;
import ua.pidopryhora.mediaconverter.auth.controller.WithMockUserSecurityContextFactory;

@SpringBootApplication
@EntityScan("ua.pidopryhora.mediaconverter.auth.entity")
public class TestApplication {
}