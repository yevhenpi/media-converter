package ua.pidopryhora.mediaconverter.requestmanager.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.util.HashUtil;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class IdempotencyServiceTest {

    @Mock
    private HashUtil hashUtil;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @InjectMocks
    private IdempotencyService idempotencyService;

    @BeforeEach
    void setUp() {

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

    }

    @Test
    void testAddIdempotencyKey_Success() {
        // Arrange
        RequestDTO requestDTO = new UploadRequestDTO();
        String key = "sampleKey";
        when(hashUtil.getHash(requestDTO)).thenReturn(key);
        when(redisTemplate.opsForValue().setIfAbsent(eq(key), anyString(), eq(IdempotencyService.IDEMPOTENCY_TTL_SECONDS), eq(TimeUnit.SECONDS)))
                .thenReturn(true);

        // Act
        boolean result = idempotencyService.addIdempotencyKey(requestDTO);

        // Assert
        assertTrue(result);
        verify(valueOperations, times(1)).setIfAbsent(eq(key), anyString(), eq(IdempotencyService.IDEMPOTENCY_TTL_SECONDS), eq(TimeUnit.SECONDS));
    }

    @Test
    void testAddIdempotencyKey_Failure() {
        // Arrange
        RequestDTO requestDTO = new UploadRequestDTO();
        String key = "sampleKey";
        when(hashUtil.getHash(requestDTO)).thenReturn(key);
        when(redisTemplate.opsForValue().setIfAbsent(eq(key), anyString(), eq(IdempotencyService.IDEMPOTENCY_TTL_SECONDS), eq(TimeUnit.SECONDS)))
                .thenReturn(false);

        // Act
        boolean result = idempotencyService.addIdempotencyKey(requestDTO);

        // Assert
        assertFalse(result);
        verify(valueOperations, times(1)).setIfAbsent(eq(key), anyString(), eq(IdempotencyService.IDEMPOTENCY_TTL_SECONDS), eq(TimeUnit.SECONDS)); // verify exactly once
    }

    @Test
    void testAddIdempotencyKey_NullReturn() {
        // Arrange
        RequestDTO requestDTO = new UploadRequestDTO();
        String key = "sampleKey";
        when(hashUtil.getHash(requestDTO)).thenReturn(key);
        when(redisTemplate.opsForValue().setIfAbsent(eq(key), anyString(), eq(IdempotencyService.IDEMPOTENCY_TTL_SECONDS), eq(TimeUnit.SECONDS)))
                .thenReturn(null);

        // Act
        boolean result = idempotencyService.addIdempotencyKey(requestDTO);

        // Assert
        assertFalse(result);
        verify(valueOperations, times(1)).setIfAbsent(eq(key), anyString(), eq(IdempotencyService.IDEMPOTENCY_TTL_SECONDS), eq(TimeUnit.SECONDS)); // verify exactly once
    }
}
