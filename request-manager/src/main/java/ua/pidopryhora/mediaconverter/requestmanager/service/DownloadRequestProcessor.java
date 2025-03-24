package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.s3.S3PresignedUrlService;

import java.util.Map;
@Service
@AllArgsConstructor
@Validated
public class DownloadRequestProcessor implements RequestProcessor<DownloadRequestDTO> {

    private final S3PresignedUrlService presignedUrlService;

    @Override
    public ResponseEntity<?> processRequest(@Valid DownloadRequestDTO requestDTO) {

        String url = presignedUrlService.generateDownloadPresignedUrl(requestDTO.getJobId()).toString();

        return ResponseEntity.ok(Map.of("url",url,
                "jobId", requestDTO.getJobId()));
    }
}
