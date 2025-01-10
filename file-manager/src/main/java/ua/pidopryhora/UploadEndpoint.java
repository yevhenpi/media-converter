package ua.pidopryhora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/file-manager")
public class UploadEndpoint {

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("format") String format){
        log.info("FILE IS HERE");

        if (file.isEmpty()) {
            log.info("FILE IS EMPTY");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "File is empty."));
        }

        if (format == null || format.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "File format is missing."));
        }

        try {


            return ResponseEntity.ok(Map.of(
                    "message", "File is being uploaded",
                    "fileName", file.getOriginalFilename(),
                    "format", format
            ));
        } catch (Exception e) {
            log.error("ERROR LOADING MESSAGE TO RABBITMQ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to upload file."));
        }

    }
}
