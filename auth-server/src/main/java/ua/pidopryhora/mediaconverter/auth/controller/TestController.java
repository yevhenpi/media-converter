package ua.pidopryhora.mediaconverter.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipal;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class TestController {

    @GetMapping("/test")
    public String getTest(@AuthenticationPrincipal UserPrincipal principal){

        return "You are " + principal.getEmail();
    }
}
