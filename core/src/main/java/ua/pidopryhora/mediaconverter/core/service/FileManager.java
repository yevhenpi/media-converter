package ua.pidopryhora.mediaconverter.core.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.core.s3.S3Downloader;
import ua.pidopryhora.mediaconverter.core.s3.S3Uploader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Slf4j
@Service

public class FileManager implements IFileManager{

    //TODO: Add scheduled removing of local files

    private final String INPUT_DIRECTORY = "download_dir";
    private final String OUTPUT_DIRECTORY = "upload_dir";

    private final S3Uploader s3Uploader;
    private final S3Downloader s3Downloader;

    public FileManager(S3Uploader s3Uploader, S3Downloader s3Downloader) {
        this.s3Uploader = s3Uploader;
        this.s3Downloader = s3Downloader;
        createDirectory();
    }

    @Override
    public String downloadFile(String key) {

        if(isFileStoredLocally(key)) return getInputFilePath(key);

        return s3Downloader.download(key, Paths.get(getInputFilePath(key)));
    }

    @Override
    public boolean uploadFile(Path path) {
        s3Uploader.upload(path);
        return deleteLocalFile(path);
    }

    private void createDirectory(){
        Path inputDirectory = Paths.get(INPUT_DIRECTORY);
        Path outputDirectory = Paths.get(OUTPUT_DIRECTORY);
        try {
            if (!Files.exists(inputDirectory)) {
                Files.createDirectories(inputDirectory);
            }
            if(!Files.exists(outputDirectory)){
                Files.createDirectories(outputDirectory);
            }
        } catch (IOException e) {
            log.error("CAN NOT CREATE DIRECTORIES", e);
        }
    }

    public String getTargetPath(String fileName, String targetFormat){
        int dotIndex = fileName.lastIndexOf('.');

        return OUTPUT_DIRECTORY + "/"+ fileName.substring(0,dotIndex+1) + targetFormat;

    }

    public boolean deleteLocalFile(Path path){
        File file = new File(String.valueOf(path));
        return file.delete();

    }

    public boolean isFileStoredLocally(String s3Key) {
        return Files.exists(Paths.get(INPUT_DIRECTORY, s3Key));
    }


    public String getInputFilePath(String s3Key) {
        return Paths.get(INPUT_DIRECTORY, s3Key).toString();
    }

    public String getOutputFilePath(String s3Key) {
        return Paths.get(OUTPUT_DIRECTORY, s3Key).toString();
    }
}

