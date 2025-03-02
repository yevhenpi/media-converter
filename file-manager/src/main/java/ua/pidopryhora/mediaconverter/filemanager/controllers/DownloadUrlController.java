package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
@CrossOrigin(origins = "*")
public class DownloadUrlController {
    private final S3PresignedUrlService presignedUrlService;

    @GetMapping("/download")
    public ResponseEntity<?> extractRequestData(@RequestBody DownloadRequestDTO requestDTO){

        String url = presignedUrlService.generatePresignedUrl(requestDTO.getJobId()).toString();


        return ResponseEntity.ok(Map.of("url",url,
                "jobId", requestDTO.getJobId()));
    }
}
