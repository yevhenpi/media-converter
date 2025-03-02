package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.redis.RedisCachingService;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

import java.time.Duration;

@Service
public class UploadRequestCachingService extends RedisCachingService<UploadRequestDTO> {

    private final long TTL_DURATION = 15L;



    public UploadRequestCachingService(RedisTemplate<String, UploadRequestDTO> objectRedisTemplate) {
        super(objectRedisTemplate);
    }


    public void cacheData(UploadRequestDTO requestDTO) {
        cacheData(requestDTO.getS3Key(), requestDTO, Duration.ofMinutes(TTL_DURATION));
    }

    public void removeFileData(String s3Key) {
        removeData(s3Key);
    }

    public UploadRequestDTO getFileData(String s3Key) {
        return getData(s3Key);
    }
}
