package fun.lifepoem.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Yiwyn
 * @create 2023/2/16 21:37
 */
@Service
public class RedisService<T> {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;


    public void setKey(String key, T data) {
        redisTemplate.opsForValue().set(key, data);
    }

    public void setKey(String key, T data, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, data, time, timeUnit);
    }

    public T getKey(String key) {
        T data = redisTemplate.opsForValue().get(key);
        return data;
    }

    public Long deleteKey(String... keys) {
        Long delete = redisTemplate.delete(Arrays.asList(keys));
        return delete;
    }


}
