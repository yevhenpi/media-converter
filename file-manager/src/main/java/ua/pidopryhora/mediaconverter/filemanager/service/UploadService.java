package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserRole;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;
import ua.pidopryhora.mediaconverter.filemanager.service.upload.UploadHandler;

import java.net.URL;
import java.util.Map;

import static ua.pidopryhora.mediaconverter.filemanager.model.UserRole.*;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final S3PresignedUrlService presignedUrlService;
    private final Map<String, UploadHandler> uploadHandlers;

    public ResponseEntity<?> handleUploadRequest(RequestDataDTO metadata, UserDataDTO userDataDTO){

        UploadHandler handler = getHandlerByRole(UserRole.valueOf(userDataDTO.getUserRole()));

        return handler.generateResponse(metadata, userDataDTO, presignedUrlService);



    }

    private UploadHandler getHandlerByRole(UserRole role) {
        return switch (role) {
            case USER -> uploadHandlers.get("userUploadHandler");
            case PREMIUM -> uploadHandlers.get("premiumUploadHandler");
            case ADMIN -> uploadHandlers.get("adminUploadHandler");
        };
    }
}
