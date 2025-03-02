package ua.pidopryhora.mediaconverter.core.service;

import java.nio.file.Path;

public interface IFileManager {
    String downloadFile(String key);
    boolean uploadFile(Path path);


}
