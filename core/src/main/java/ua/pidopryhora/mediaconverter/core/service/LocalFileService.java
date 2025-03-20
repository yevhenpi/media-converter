package ua.pidopryhora.mediaconverter.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.core.config.DirectoryConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
public class LocalFileService {

    public boolean deleteFile(Path path) {
        try {
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Failed to delete file: {}", path, e);
            return false;
        }
    }

    public boolean isFileStoredLocally(Path path) {
        return Files.exists(path);
    }

    public Path getTargetPath(String jobId, String targetFormat) {
        return DirectoryConstants.OUTPUT_DIRECTORY.resolve(jobId + "." + targetFormat);
    }

    public Path getInputFilePath(String key) {
        return DirectoryConstants.INPUT_DIRECTORY.resolve(key);
    }

    public Path getOutputFilePath(String key) {
        return DirectoryConstants.OUTPUT_DIRECTORY.resolve(key);
    }
}