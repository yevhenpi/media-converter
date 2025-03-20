package ua.pidopryhora.mediaconverter.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.core.config.DirectoryConstants;
import ua.pidopryhora.mediaconverter.core.s3.S3Downloader;
import ua.pidopryhora.mediaconverter.core.s3.S3Uploader;

import java.nio.file.Path;
@Slf4j
@Service
public class S3StorageService implements IStorageService{

    private final S3Uploader s3Uploader;
    private final S3Downloader s3Downloader;

    public S3StorageService(S3Uploader s3Uploader, S3Downloader s3Downloader) {
        this.s3Uploader = s3Uploader;
        this.s3Downloader = s3Downloader;
    }
    @Override
    public Path downloadFile(String key) {
        Path localPath = DirectoryConstants.INPUT_DIRECTORY.resolve(key);
        return s3Downloader.download(key, localPath);
    }

    @Override
    public void uploadFile(Path path) {
        s3Uploader.upload(path);

    }
}
