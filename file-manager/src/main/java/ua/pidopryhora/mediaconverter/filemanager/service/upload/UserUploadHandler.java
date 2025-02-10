package ua.pidopryhora.mediaconverter.filemanager.service.upload;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;

@Service("userUploadHandler")
@RequiredArgsConstructor
public class UserUploadHandler implements UploadHandler{

    @Override
    public ResponseEntity<?> generateResponse(RequestDataDTO metadata, UserDataDTO user, S3PresignedUrlService presignedUrlService) {
        return null;
    }
}
