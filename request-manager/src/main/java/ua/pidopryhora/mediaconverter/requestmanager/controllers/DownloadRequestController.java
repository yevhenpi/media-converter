package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
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

    @GetMapping("/download")
    public ResponseEntity<?> extractRequestData(@Valid DownloadRequestDTO request){

        String url = presignedUrlService.generateDownloadPresignedUrl(request.getJobId()).toString();


        return ResponseEntity.ok(Map.of("url",url,
                "jobId", request.getJobId()));
    }
}
