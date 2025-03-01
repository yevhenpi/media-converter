package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.util.concurrent.TimeUnit;

import static ua.pidopryhora.mediaconverter.common.model.JobStatus.PROCESSING;

@Service
public class IdempotencyService {
    private final HashUtil hashUtil;


    private static final long IDEMPOTENCY_TTL_SECONDS = 60;

    private final RedisTemplate<String, String> redisTemplate;

    public IdempotencyService(HashUtil hashUtil, RedisTemplate<String, String> redisTemplate) {
        this.hashUtil = hashUtil;
        this.redisTemplate = redisTemplate;
    }

    public boolean addIdempotencyKey(RequestDTO requestDTO) {
        var key = hashUtil.getHash(requestDTO);
        Boolean added = redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(PROCESSING), IDEMPOTENCY_TTL_SECONDS, TimeUnit.SECONDS);
        return added != null && added;
    }


}

