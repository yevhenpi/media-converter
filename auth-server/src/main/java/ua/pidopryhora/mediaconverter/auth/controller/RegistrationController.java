package ua.pidopryhora.mediaconverter.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @PostMapping("/register")
    public ResponseEntity<Object> register(){
        return ResponseEntity.ok(Map.of("message","Registered successfully"));
    }
}
