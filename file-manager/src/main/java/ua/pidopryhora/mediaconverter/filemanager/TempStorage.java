package ua.pidopryhora.mediaconverter.filemanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class TempStorage {


    private final String UPLOAD_DIR;


    public TempStorage(@Value("${upload.dir}") String UPLOAD_DIR){

        File uploadDir = new File(UPLOAD_DIR);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        this.UPLOAD_DIR = UPLOAD_DIR;
    }

    public Path saveFile(MultipartFile file){

        var filename = file.getOriginalFilename();

        try {
            Path filePath = Paths.get(UPLOAD_DIR, filename);
            Files.write(filePath, file.getBytes());
            log.debug("File {} saved to local storage", filename);

            return filePath;

        } catch (IOException e) {
            log.error("CANT SAVE FILE TO LOCAL STORAGE", e);
        }
        return null;
    }

    public void deleteFile(Path path){

        var fileName = path.getFileName().toString();

        try {
            Files.delete(path);
            log.debug("File {} removed from local storage", fileName);
        } catch (IOException e) {
            log.error("ERROR DELETING FILE FROM LOCAL STORAGE", e);
        }
    }
}
