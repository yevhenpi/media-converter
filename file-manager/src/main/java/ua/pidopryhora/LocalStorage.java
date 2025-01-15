package ua.pidopryhora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class LocalStorage {


    private final String UPLOAD_DIR;


    public LocalStorage(@Value("${upload.dir}") String UPLOAD_DIR){
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        this.UPLOAD_DIR = UPLOAD_DIR;
    }

    public Path saveFile(MultipartFile file){

        String filename = file.getOriginalFilename();

        try {
            Path filePath = Paths.get(UPLOAD_DIR, filename);
            Files.write(filePath, file.getBytes());
            log.info("File {} saved to local storage", filename);

            return filePath;
        } catch (IOException e) {
            log.error("CANT SAVE FILE TO LOCAL STORAGE", e);
        }
        return null;
    }

    public void deleteFile(Path path){
        String fileName = path.getFileName().toString();
        try {
            Files.delete(path);
            log.info("File {} removed from local storage", fileName);
        } catch (IOException e) {
            log.error("ERROR DELETING FILE FROM LOCAL STORAGE", e);
        }
    }
}
