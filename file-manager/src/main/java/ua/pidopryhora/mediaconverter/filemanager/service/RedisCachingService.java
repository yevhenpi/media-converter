package ua.pidopryhora.mediaconverter.filemanager.service;



import org.springframework.data.redis.core.RedisTemplate;
import java.time.Duration;

public abstract class RedisCachingService<T> implements CachingService<T>{

    protected final RedisTemplate<String, T> objectRedisTemplate;

    protected RedisCachingService(RedisTemplate<String, T> objectRedisTemplate) {
        this.objectRedisTemplate = objectRedisTemplate;
    }

    public void cacheData(String key, T value, Duration ttl) {
        objectRedisTemplate.opsForValue().setIfAbsent(key, value, ttl);
    }

    public void removeData(String key) {
        objectRedisTemplate.delete(key);
    }

    public T getData(String key) {
        return objectRedisTemplate.opsForValue().get(key);
    }
}
