package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.RequestProcessor;
import ua.pidopryhora.mediaconverter.requestmanager.service.s3.S3PresignedUrlService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
@CrossOrigin(origins = "*")
@Validated
public class DownloadRequestController {

    private final S3PresignedUrlService presignedUrlService;
    private final RequestProcessor<DownloadRequestDTO> requestProcessor;

    @GetMapping("/download")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserId") String userId,
                                                @RequestHeader("UserRole") String userRole,
                                                DownloadRequestDTO requestDTO){

        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

        return requestProcessor.processRequest(requestDTO);
    }
}
