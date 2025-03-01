package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.common.jave2.JAVEDataSupplier;


import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InfoController {

    private final JAVEDataSupplier javeDataSupplier;

    @GetMapping("/file")
    public ResponseEntity<?> getFile(@RequestHeader("UserRole") String role,
                          @RequestHeader("UserId") String userId){

        return ResponseEntity.ok(Map.of(
                                        "UserId", userId,
                                        "UserRole", role));
    }

    @GetMapping("/audio/formats")
    public ResponseEntity<?> getFormats(){

        return ResponseEntity.ok(javeDataSupplier.getAudioFormats());
    }

    @GetMapping("/encoders")
    public ResponseEntity<?> getEncoders(@RequestParam String type){

        if(type.equals("audio")) {
            return ResponseEntity.ok(javeDataSupplier.getAudioEncoders());
        } else if (type.equals("video")) {
            return ResponseEntity.ok(javeDataSupplier.getVideoEncoders());
        }


        return ResponseEntity.badRequest().body(Map.of("message", "wrong type"));
    }

    @GetMapping("/decoders")
    public ResponseEntity<?> getDecoders(@RequestParam String type){

        if(type.equals("audio")) {
            return ResponseEntity.ok(javeDataSupplier.getAudioDecoders());
        } else if (type.equals("video")) {
            return ResponseEntity.ok(javeDataSupplier.getVideoDecoders());
        }


        return ResponseEntity.badRequest().body(Map.of("message", "wrong type"));
    }

}
