package ua.pidopryhora.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.pidopryhora.UploadProcessor;
import ua.pidopryhora.model.MediaMessage;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/file-manager")
public class UploadEndpoint {

    private final UploadProcessor uploadProcessor;

    public UploadEndpoint(UploadProcessor uploadProcessor) {
        this.uploadProcessor = uploadProcessor;
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("format") String format) throws InterruptedException {

        if (file.isEmpty()) {
            log.info("FILE IS EMPTY");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "File is empty."));
        }

        if (format == null || format.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "File format is missing."));
        }


        MediaMessage mediaMessage = new MediaMessage(file, format);
        uploadProcessor.process(mediaMessage);


        return ResponseEntity.ok(Map.of(
                "message", "File is being uploaded",
                "fileName", file.getOriginalFilename()
        ));


    }
}
