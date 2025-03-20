package ua.pidopryhora.mediaconverter.core.service;

import java.nio.file.Path;
//TODO:Add failed upload flow
public interface IFileManager {
    Path downloadFile(String key);
    boolean uploadFile(Path path);


}
