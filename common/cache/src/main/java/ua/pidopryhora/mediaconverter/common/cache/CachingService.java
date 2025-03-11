package ua.pidopryhora.mediaconverter.common.cache;

import java.time.Duration;

public interface CachingService<T> {
    void cacheData(String key, T value, Duration ttl);
    void removeData(String key);
    T getData(String key);
}
