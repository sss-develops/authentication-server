package project.goorm.authentication.test.integrationtest.client.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static com.mongodb.assertions.Assertions.assertTrue;

@DisplayName("레디스 세션 삭제 통합 테스트")
class RedisSessionDeleteIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RedisSessionService redisSessionService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName("세션을 삭제하면 해당 키에 대한 레디스 값이 비게 된다.")
    void when_delete_session_then_redis_value_should_be_empty() {
        String session = redisSessionService.save(1L);
        redisSessionService.deleteSession(1L, session);

        assertTrue(redisTemplate.opsForSet().members("member:id:1:sessions").isEmpty());
    }

    @Test
    @DisplayName("모든 세션을 삭제하면 해당 키에 대한 레디스 값들이 비게 된다.")
    void when_delete_all_sessions_then_redis_values_should_be_empty() {
        redisSessionService.save(1L);
        redisSessionService.deleteAllSessions(1L);

        assertTrue(redisTemplate.opsForSet().members("member:id:1:sessions").isEmpty());
    }
}
