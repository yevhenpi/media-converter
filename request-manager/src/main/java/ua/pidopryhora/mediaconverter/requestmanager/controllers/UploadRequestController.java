package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.RequestProcessor;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UploadRequestController {

    private final RequestProcessor<UploadRequestDTO> uploadRequestProcessor;

    @PostMapping("/files")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserId") String userId,
                                                @RequestHeader("UserRole") String userRole,
                                                @RequestBody UploadRequestDTO requestDTO) {
        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

        return uploadRequestProcessor.processRequest(requestDTO);
    }
}
