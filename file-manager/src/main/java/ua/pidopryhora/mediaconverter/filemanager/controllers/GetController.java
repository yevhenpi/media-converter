package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class GetController {

    @GetMapping("/file")
    public String getFile(@RequestHeader("UserRole") String role,
                          @RequestHeader("UserId") String userId){
        log.debug("{}{}", role, userId);
        return role + userId;
    }

}
