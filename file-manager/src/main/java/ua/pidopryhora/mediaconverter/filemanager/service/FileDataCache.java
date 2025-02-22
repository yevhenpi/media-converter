package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

import java.time.Duration;

@Service
@AllArgsConstructor
public class FileDataCache {

    private final RedisTemplate<String, Object> objectRedisTemplate;

    public void cashFileData(UploadRequestDTO requestDTO){

        objectRedisTemplate.opsForValue().setIfAbsent(requestDTO.getFileName(), requestDTO, Duration.ofMinutes(15));

    }


    public void removeFileData(String fileName){
        objectRedisTemplate.delete(fileName);

    }

    public Object getFileData(String fileName){
        return objectRedisTemplate.opsForValue().get(fileName);
    }
}
