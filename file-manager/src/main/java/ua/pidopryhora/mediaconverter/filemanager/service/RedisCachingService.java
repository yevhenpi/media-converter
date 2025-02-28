package ua.pidopryhora.mediaconverter.filemanager.service;



import org.springframework.data.redis.core.RedisTemplate;
import java.time.Duration;

public abstract class RedisCachingService<T> implements CachingService<T>{

    protected final RedisTemplate<String, T> redisTemplate;

    protected RedisCachingService(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheData(String key, T value, Duration ttl) {
        redisTemplate.opsForValue().setIfAbsent(key, value, ttl);
    }

    public void removeData(String key) {
        redisTemplate.delete(key);
    }

    public T getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
