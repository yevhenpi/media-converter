package ua.pidopryhora.mediaconverter.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class GetController {

    @GetMapping("/test")
    public ResponseEntity<Object> getTest(){
        return ResponseEntity.ok(Map.of("message","Success"));
    }
}
