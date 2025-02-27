package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

import java.time.Duration;

@Service
public class FileDataCache extends AbstractCachingService<UploadRequestDTO> {

    private final long TTL_DURATION = 15L;


    public FileDataCache(RedisTemplate<String, UploadRequestDTO> redisTemplate) {
        super(redisTemplate);
    }

    public void cacheFileData(UploadRequestDTO requestDTO) {
        cacheData(requestDTO.getFileName(), requestDTO, Duration.ofMinutes(TTL_DURATION));
    }

    public void removeFileData(String fileName) {
        removeData(fileName);
    }

    public UploadRequestDTO getFileData(String fileName) {
        return getData(fileName);
    }
}
