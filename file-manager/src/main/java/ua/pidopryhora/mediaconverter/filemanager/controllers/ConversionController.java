package ua.pidopryhora.mediaconverter.filemanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.AudioRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.ConversionRequestProcessor;
import ua.pidopryhora.mediaconverter.filemanager.service.IdempotencyService;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/audio")
@Validated
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionRequestProcessor requestProcessor;

    @PostMapping("/convert")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserRole") String userRole,
                                                @RequestHeader("UserId") String userId,
                                                @RequestBody AudioRequestDTO requestDTO){

        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

//        String hash = hashUtil.getHash(requestDTO);
//        if(!idempotencyService.addIdempotencyKey(hash)){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error","request is already being processed"));
//        }
//        log.debug("hash {}", hash);



        return requestProcessor.process(requestDTO);
    }
}
