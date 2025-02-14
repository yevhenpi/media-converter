package ua.pidopryhora.mediaconverter.filemanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.ConversionRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.ConversionRequestProcessor;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionRequestProcessor requestProcessor;

    @PostMapping("/convert")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserRole") String userRole,
                                                @RequestHeader("UserId") String userId,
                                                @Valid@RequestBody ConversionRequestDTO requestDTO){

        String requestId = UUID.randomUUID().toString();
        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));
        requestDTO.setRequestId(requestId);


        return requestProcessor.process(requestDTO);
    }
}
