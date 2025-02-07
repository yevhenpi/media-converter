package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestData;
import ua.pidopryhora.mediaconverter.filemanager.model.UserDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;

import java.net.URL;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final S3PresignedUrlService presignedUrlService;

    public ResponseEntity<?> handleUploadRequest(RequestData metadata, UserDataDTO userDataDTO){

        URL presignedUrl = presignedUrlService.generatePresignedUrl(metadata.getFileName());

        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString())
        );


    }
}
