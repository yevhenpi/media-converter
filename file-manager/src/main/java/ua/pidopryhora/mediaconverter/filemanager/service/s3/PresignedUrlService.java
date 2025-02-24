package ua.pidopryhora.mediaconverter.filemanager.service.s3;

import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

import java.net.URL;

public interface PresignedUrlService {

    URL generatePresignedUrl(UploadRequestDTO requestDTO);
}
