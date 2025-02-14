package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.jave2.FormatValidationService;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;

import java.net.URL;
import java.util.Map;
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    private final S3PresignedUrlService presignedUrlService;
    private final FormatValidationService formatValidationService;
    private final FileSizeValidationService sizeValidationService;
    private final CashingService cashingService;

    public ResponseEntity<?> handleUploadRequest(UploadRequestDTO metadata){

        if(!formatValidationService.isFormatValid(metadata)){
               return ResponseEntity.badRequest().body(Map.of("error", "format is not supported"));
        }
        if(!sizeValidationService.isSizeValid(metadata)){
            return ResponseEntity.badRequest().body(Map.of("error", "file is too big"));
        }


        URL presignedUrl = presignedUrlService.generatePresignedUrl(metadata);

        cashingService.cashMetaData(metadata);


        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString(),
                "requestId", metadata.getRequestId()));

    }


}
