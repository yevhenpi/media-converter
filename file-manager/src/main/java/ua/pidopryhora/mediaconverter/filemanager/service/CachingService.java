package ua.pidopryhora.mediaconverter.filemanager.service;

import java.time.Duration;

public interface CachingService<T> {
    void cacheData(String key, T value, Duration ttl);
    void removeData(String key);
    T getData(String key);
}
