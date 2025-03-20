package ua.pidopryhora.mediaconverter.core.service;

import java.nio.file.Path;

public interface IStorageService {
    Path downloadFile(String key);
    void uploadFile(Path path);
}
