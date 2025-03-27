package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.s3.S3PresignedUrlService;

import java.util.Map;
@Service
@AllArgsConstructor
@Validated
public class DownloadRequestProcessor implements RequestProcessor<DownloadRequestDTO> {

    private final S3PresignedUrlService presignedUrlService;
    private final DownloadRequestValidationService validationService;
    private final JobDataService jobDataService;

    @Override
    public ResponseEntity<?> processRequest(@Valid DownloadRequestDTO requestDTO) {

        validationService.validateRequest(requestDTO);

        String key = jobDataService.getJob(requestDTO.getJobId()).getS3Key();

        String url = presignedUrlService.generateDownloadPresignedUrl(key).toString();

        return ResponseEntity.ok(Map.of("url",url,
                "jobId", requestDTO.getJobId()));
    }
}
