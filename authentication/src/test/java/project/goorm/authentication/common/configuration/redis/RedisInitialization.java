package project.goorm.authentication.common.configuration.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class RedisInitialization {

    @Autowired
    @Resource(name = "sessionsRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Resource(name = "sessionRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Resource(name = "loginCountRedisTemplate")
    private RedisTemplate<String, Long> longRedisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public RedisTemplate<String, Long> getLongRedisTemplate() {
        return longRedisTemplate;
    }

    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        Set<String> redisKeys = redisTemplate.keys("member:id*");

        if (redisKeys == null) {
            redisKeys = new HashSet<>();
        }

        for (String key : redisKeys) {
            redisTemplate.delete(key);
        }

        for (String key : redisKeys) {
            stringRedisTemplate.delete(key);
        }

        redisKeys = longRedisTemplate.keys("member:id*");
        for (String key : redisKeys) {
            longRedisTemplate.delete(key);
        }
    }
}
