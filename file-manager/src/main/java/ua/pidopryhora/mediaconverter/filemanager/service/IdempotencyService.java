package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class IdempotencyService {

    // Name of the Redis set where we'll store idempotency keys
    private static final String IDEMPOTENCY_SET = "idempotency_keys";
    private static final long IDEMPOTENCY_TTL_SECONDS = 60;

    private final RedisTemplate<String, String> redisTemplate;


    public IdempotencyService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Attempts to add the idempotency key to the set.
     * Returns true if the key was added (i.e. it did not exist before), or false if it was already present.
     */
    public boolean addIdempotencyKey(String key) {
        // "true" is a placeholder value; it doesn't matter what the value is.
        Boolean added = redisTemplate.opsForValue().setIfAbsent(key, "true", IDEMPOTENCY_TTL_SECONDS, TimeUnit.SECONDS);
        return added != null && added;
    }

    /**
     * Checks if the given idempotency key is already present in the set.
     */
    public boolean isKeyPresent(String key) {
        Boolean isMember = redisTemplate.opsForSet().isMember(IDEMPOTENCY_SET, key);
        return isMember != null && isMember;
    }
}

