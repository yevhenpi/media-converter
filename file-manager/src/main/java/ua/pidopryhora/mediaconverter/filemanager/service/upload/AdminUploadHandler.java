package ua.pidopryhora.mediaconverter.filemanager.service.upload;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.MetadataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;

import java.net.URL;
import java.util.Map;

@Service("adminUploadHandler")
@RequiredArgsConstructor
public class AdminUploadHandler implements UploadHandler{
    @Override
    public ResponseEntity<?> generateResponse(MetadataDTO metadata, UserDataDTO user, S3PresignedUrlService presignedUrlService) {

        URL presignedUrl = presignedUrlService.generatePresignedUrl(metadata.getFileName());

        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString()));
    }
}
