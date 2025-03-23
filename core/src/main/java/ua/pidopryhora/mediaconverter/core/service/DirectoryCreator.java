package ua.pidopryhora.mediaconverter.core.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.core.config.DirectoryConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class DirectoryCreator {

    public DirectoryCreator() {
        createDirectories();
    }

    private void createDirectories() {
        createDirectory(DirectoryConstants.INPUT_DIRECTORY);
        createDirectory(DirectoryConstants.OUTPUT_DIRECTORY);
    }

    public void createDirectory(Path path) {
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path);
                log.info("Created directory: {}", path);
            }
        } catch (IOException e) {
            log.error("Failed to create directory: {}", path, e);
        }
    }
}