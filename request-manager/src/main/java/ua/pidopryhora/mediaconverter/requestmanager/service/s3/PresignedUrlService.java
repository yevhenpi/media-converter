package ua.pidopryhora.mediaconverter.requestmanager.service.s3;

import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;

import java.net.URL;

public interface PresignedUrlService {
    URL generatePresignedUrl(UploadRequestDTO requestDTO);
}
