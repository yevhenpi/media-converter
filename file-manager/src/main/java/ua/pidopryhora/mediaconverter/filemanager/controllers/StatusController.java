package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/job")
public class StatusController {

    @GetMapping("/status")
    public String getStatus(@RequestHeader("UserRole") String role,
                            @RequestHeader("UserId") String userId,
                            @RequestHeader("FileName") String fileName){
        log.debug("{}{}", role, userId);
        return role + userId;
    }
}
