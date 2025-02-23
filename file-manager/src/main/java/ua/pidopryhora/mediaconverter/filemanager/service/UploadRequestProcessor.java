package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.jave2.FormatValidationService;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.S3PresignedUrlService;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.net.URL;
import java.util.Map;
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadRequestProcessor {

    //TODO: Encapsulate validation logic.

    private final S3PresignedUrlService presignedUrlService;
    private final FileSizeValidationService sizeValidationService;
    private final FileDataCache fileDataCache;
    private final HashUtil hashUtil;
    private final FileDataService fileDataService;

    public ResponseEntity<?> handleUploadRequest(UploadRequestDTO requestDTO){


        if(!sizeValidationService.isSizeValid(requestDTO)){
            return ResponseEntity.badRequest().body(Map.of("error", "file is too big"));
        }

        if (fileDataService.isPresent(requestDTO.getFileName())) return ResponseEntity.badRequest().body(Map.of("error", "file is already uploaded"));



        URL presignedUrl = presignedUrlService.generatePresignedUrl(requestDTO);

        fileDataCache.cashFileData(requestDTO);


        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString(),
                "hash", hashUtil.getHash(requestDTO)));

    }




}
