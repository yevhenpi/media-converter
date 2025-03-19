package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.s3.PresignedUrlService;


import java.net.URL;
import java.util.Map;
@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UploadRequestProcessor implements RequestProcessor<UploadRequestDTO> {

    private final PresignedUrlService presignedUrlService;
    private final UploadRequestCachingService uploadRequestCachingService;

    @Override
    public ResponseEntity<?> processRequest(@Valid UploadRequestDTO requestDTO){

        URL presignedUrl = presignedUrlService.generatePresignedUrl(requestDTO);
        uploadRequestCachingService.cacheData(requestDTO);

        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString()));

    }




}
