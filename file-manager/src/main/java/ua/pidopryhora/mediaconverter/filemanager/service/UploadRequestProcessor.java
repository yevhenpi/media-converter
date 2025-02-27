package ua.pidopryhora.mediaconverter.filemanager.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.s3.PresignedUrlService;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.net.URL;
import java.util.Map;
@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UploadRequestProcessor implements RequestProcessor<UploadRequestDTO> {

    //TODO: Encapsulate validation logic.

    private final PresignedUrlService presignedUrlService;
    private final FileDataCache fileDataCache;
    private final HashUtil hashUtil;

    @Override
    public ResponseEntity<?> processRequest(@Valid UploadRequestDTO requestDTO){

        String hash = hashUtil.getHash(requestDTO);

        URL presignedUrl = presignedUrlService.generatePresignedUrl(requestDTO);
        fileDataCache.cacheFileData(requestDTO);


        return ResponseEntity.ok().body(Map.of(
                "url", presignedUrl.toString(),
                "hash", hash));

    }




}
