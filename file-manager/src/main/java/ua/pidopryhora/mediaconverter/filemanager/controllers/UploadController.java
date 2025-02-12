package ua.pidopryhora.mediaconverter.filemanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestMetadataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.UploadService;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Validated
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserId") String userId,
                                                @RequestHeader("UserRole") String userRole,
                                                @Valid @RequestBody RequestMetadataDTO requestMetadataDTO) {

        requestMetadataDTO.setRole(userRole);
        requestMetadataDTO.setUserId(Long.parseLong(userId));


        return uploadService.handleUploadRequest(requestMetadataDTO);

    }
}
