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
            @RequestParam("fileName") String fileName,
            @RequestParam(value = "expirationMinutes", defaultValue = "15") long expirationMinutes) {

        URL presignedUrl = presignedUrlService.generatePresignedUrl(fileName, expirationMinutes);

        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString()

        ));
    }
}
