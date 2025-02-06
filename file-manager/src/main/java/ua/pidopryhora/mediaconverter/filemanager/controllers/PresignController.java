package ua.pidopryhora.mediaconverter.filemanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;

import java.net.URL;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PresignController {

    private final S3PresignedUrlService presignedUrlService;

    public PresignController(S3PresignedUrlService presignedUrlService) {
        this.presignedUrlService = presignedUrlService;
    }

    @GetMapping("/upload")
    public ResponseEntity<?> getPresignedUrl(
            @RequestHeader("FileName") String fileName,
            @RequestHeader("FileSize") String fileSize,
            @RequestHeader("TargetFormat") String format,
            @RequestHeader("UserId") String userId,
            @RequestHeader("UserRole") String role) {

        URL presignedUrl = presignedUrlService.generatePresignedUrl(fileName);

        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString(),
                "role", role
        ));
    }
}
