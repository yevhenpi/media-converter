package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

@Slf4j
@Service
public class FileSizeValidationService {

    //TODO: Create more centralised system for userRoles permissions and limits

    private final long USER_SIZE_LIMIT = 10485760L;
    private final long PREMIUM_SIZE_LIMIT = 104857600L;
    private final long ADMIN_SIZE_LIMIT = 524288000L;


    public boolean isSizeValid(UploadRequestDTO requestMetadataDTO){

        long fileSize = requestMetadataDTO.getFileSize();
        String role = requestMetadataDTO.getRole();// Normalize case to avoid case sensitivity issues

        long allowedSizeLimit = switch (role) {
            case "PREMIUM" -> PREMIUM_SIZE_LIMIT;
            case "ADMIN" -> ADMIN_SIZE_LIMIT;
            default -> USER_SIZE_LIMIT; // Default to USER limit if the role is unknown
        };


        return fileSize <= allowedSizeLimit;

    }
}
