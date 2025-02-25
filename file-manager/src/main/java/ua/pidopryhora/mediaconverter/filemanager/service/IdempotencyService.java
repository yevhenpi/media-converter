package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class IdempotencyService {


    private static final long IDEMPOTENCY_TTL_SECONDS = 60;

    private final RedisTemplate<String, String> redisTemplate;


    public IdempotencyService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public boolean addIdempotencyKey(String key) {
        // "true" is a placeholder value; it doesn't matter what the value is.
        Boolean added = redisTemplate.opsForValue().setIfAbsent(key, "true", IDEMPOTENCY_TTL_SECONDS, TimeUnit.SECONDS);
        return added != null && added;
    }


}

