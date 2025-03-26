package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.common.jave2.JAVEDataSupplier;
import ua.pidopryhora.mediaconverter.requestmanager.service.FileDataService;


import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InfoController {


    private final JAVEDataSupplier javeDataSupplier;
    private final FileDataService fileDataService;

    @GetMapping("/files")
    public ResponseEntity<?> getFile(@RequestHeader("UserId") String userId){

        return ResponseEntity.ok(fileDataService.getUserFiles(Long.valueOf(userId)));
    }

    @GetMapping("/formats/audio")
    public ResponseEntity<?> getFormats(){

        return ResponseEntity.ok(javeDataSupplier.getSupportedVideoFormats());
    }

    @GetMapping("/encoders/audio")
    public ResponseEntity<?> getEncoders(){

        return ResponseEntity.ok(javeDataSupplier.getAudioEncoders());

    }

    @GetMapping("/decoders/audio")
    public ResponseEntity<?> getDecoders(){

        return ResponseEntity.ok(javeDataSupplier.getAudioDecoders());

    }

}
