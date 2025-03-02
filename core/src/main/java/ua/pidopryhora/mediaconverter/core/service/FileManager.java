package ua.pidopryhora.mediaconverter.core.service;

import lombok.RequiredArgsConstructor;
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
        return s3Downloader.download(key, Paths.get(INPUT_DIRECTORY));
    }

    @Override
    public boolean uploadFile(Path path) {
        return s3Uploader.upload(path);
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
}
