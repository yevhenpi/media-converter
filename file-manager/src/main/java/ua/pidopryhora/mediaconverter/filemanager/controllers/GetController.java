package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class GetController {

    @GetMapping("/file")
    public ResponseEntity<?> getFile(@RequestHeader("UserRole") String role,
                          @RequestHeader("UserId") String userId){

        return ResponseEntity.ok(Map.of(
                                        "UserId", userId,
                                        "UserRole", role));
    }

}
