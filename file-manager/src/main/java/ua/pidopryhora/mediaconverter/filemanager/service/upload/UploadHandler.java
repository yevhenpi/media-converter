package ua.pidopryhora.mediaconverter.filemanager.service.upload;

import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.filemanager.model.MetadataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;

public interface UploadHandler {
    ResponseEntity<?> generateResponse(MetadataDTO metadata, UserDataDTO user, S3PresignedUrlService presignedUrlService);
}
