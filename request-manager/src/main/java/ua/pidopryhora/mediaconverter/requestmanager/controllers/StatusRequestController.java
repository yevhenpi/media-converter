package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.requestmanager.model.StatusRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.RequestProcessor;

@Slf4j
@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class StatusRequestController {

    private final RequestProcessor<StatusRequestDTO> requestProcessor;

    @PostMapping("/status")
    public ResponseEntity<?> getStatus(@RequestHeader("UserId") String userId,
                                       @RequestHeader("UserRole") String userRole,
                                       @RequestBody StatusRequestDTO requestDTO){

        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

        return requestProcessor.processRequest(requestDTO);
    }
}
