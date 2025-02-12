package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.MetadataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserDataDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UserRole;
import ua.pidopryhora.mediaconverter.filemanager.service.jave2.FormatValidationService;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;
import ua.pidopryhora.mediaconverter.filemanager.service.upload.UploadHandler;
import ws.schild.jave.EncoderException;

import java.util.Map;
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    private final S3PresignedUrlService presignedUrlService;
    private final Map<String, UploadHandler> uploadHandlers;
    private final FormatValidationService formatValidationService;

    public ResponseEntity<?> handleUploadRequest(MetadataDTO metadata, UserDataDTO userDataDTO){

            if(!formatValidationService.isFormatValid(metadata)){
               return ResponseEntity.badRequest().body(Map.of("error", "format is not valid"));
            }


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
