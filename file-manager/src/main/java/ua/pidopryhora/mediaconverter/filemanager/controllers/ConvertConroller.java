package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class ConvertConroller {

    @PostMapping("/convert")
    public String extractRequestData(@RequestHeader("UserRole") String role,
                                     @RequestHeader("UserId") String userId){
        log.debug("{}{}", role, userId);
        return role + userId;
    }
}
