package ua.pidopryhora.mediaconverter.core.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;


@Slf4j
@Service
public class FileManager implements IFileManager {

    private final IStorageService storageService;
    private final LocalFileService localFileService;

    public FileManager(IStorageService storageService, LocalFileService localFileService) {
        this.storageService = storageService;
        this.localFileService = localFileService;
    }

    @Override
    public Path downloadFile(String key) {
        Path localPath = localFileService.getInputFilePath(key);
        if (localFileService.isFileStoredLocally(localPath)) {
            return localPath;
        }
        return storageService.downloadFile(key);
    }

    @Override
    public boolean uploadFile(Path path) {
        storageService.uploadFile(path);
        return localFileService.deleteFile(path);
    }

    public Path getTargetPath(String jobId, String targetFormat) {
        return localFileService.getTargetPath(jobId, targetFormat);
    }
}


