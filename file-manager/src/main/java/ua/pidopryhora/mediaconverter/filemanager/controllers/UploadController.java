package ua.pidopryhora.mediaconverter.filemanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.UploadRequestProcessor;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@Validated
public class UploadController {

    private final UploadRequestProcessor uploadRequestProcessor;
    private final HashUtil hashUtil;

    @PostMapping("/upload")
    public ResponseEntity<?> extractRequestData(@RequestHeader("UserId") String userId,
                                                @RequestHeader("UserRole") String userRole,
                                                @Valid @RequestBody UploadRequestDTO requestDTO) {



        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

        String hash = hashUtil.getHash(requestDTO);
        log.debug("hash {}", hash);



        return uploadRequestProcessor.handleUploadRequest(requestDTO);

    }
}
