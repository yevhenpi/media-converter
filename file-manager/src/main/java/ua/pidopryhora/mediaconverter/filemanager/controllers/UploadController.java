package ua.pidopryhora.mediaconverter.filemanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.UploadRequestProcessor;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Validated
public class UploadController {

    private final UploadRequestProcessor uploadRequestProcessor;

    @PostMapping("/upload")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserId") String userId,
                                                @RequestHeader("UserRole") String userRole,
                                                @Valid @RequestBody UploadRequestDTO requestDTO) {

        String requestId = UUID.randomUUID().toString();

        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));
        requestDTO.setRequestId(requestId);


        return uploadRequestProcessor.handleUploadRequest(requestDTO);

    }
}
