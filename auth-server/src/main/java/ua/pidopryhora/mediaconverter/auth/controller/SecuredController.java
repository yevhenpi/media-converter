package ua.pidopryhora.mediaconverter.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pidopryhora.mediaconverter.common.security.UserPrincipal;

@RestController
@RequestMapping("/auth")
public class SecuredController {

    @GetMapping("/test")
    public String getTest(@AuthenticationPrincipal UserPrincipal principal){

        return "You are " + principal.getEmail();
    }
}
