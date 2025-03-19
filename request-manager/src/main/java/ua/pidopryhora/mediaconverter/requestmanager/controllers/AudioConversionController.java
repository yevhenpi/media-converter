package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioConversionRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.RequestProcessor;

@Slf4j
@RestController
@RequestMapping("/api/v1/jobs/convert")
@Validated
@RequiredArgsConstructor
public class AudioConversionController {

    private final RequestProcessor<AudioConversionRequestDTO> requestProcessor;

    @PostMapping("/audio")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserRole") String userRole,
                                                @RequestHeader("UserId") String userId,
                                                @RequestBody AudioConversionRequestDTO requestDTO){
        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

        return requestProcessor.processRequest(requestDTO);
    }
}
