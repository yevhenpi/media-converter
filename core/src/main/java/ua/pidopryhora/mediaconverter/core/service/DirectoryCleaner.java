package ua.pidopryhora.mediaconverter.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
@Slf4j
@Component
public class DirectoryCleaner {

    public  void cleanDirectory(String directory) {

        try {
            try (Stream<Path> paths = Files.walk(Path.of(directory))) {
                paths
                        .sorted(Comparator.reverseOrder())
                        .filter(path -> !path.equals(Path.of(directory)))
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                log.error("Failed to delete {}: {}", path, e.getMessage());
                            }
                        });
            }
        } catch (IOException e) {
            log.error("Error cleaning directory{}", directory);
        }
    }



}
