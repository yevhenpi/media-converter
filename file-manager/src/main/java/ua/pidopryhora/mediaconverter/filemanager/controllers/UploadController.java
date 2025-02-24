package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.RequestProcessor;
import ua.pidopryhora.mediaconverter.filemanager.service.UploadRequestProcessor;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UploadController {


    private final RequestProcessor<UploadRequestDTO> uploadRequestProcessor;

    @PostMapping("/upload")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserId") String userId,
                                                @RequestHeader("UserRole") String userRole,
                                                @RequestBody UploadRequestDTO requestDTO) {
        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

        return uploadRequestProcessor.processRequest(requestDTO);
    }
}
