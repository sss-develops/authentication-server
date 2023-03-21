package project.goorm.authentication.test.integrationtest.client.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import project.goorm.authentication.business.web.client.redis.SessionStoreService;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로그인 시도 횟수 통합 테스트")
class LoginCountIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RedisTemplate<String, Long> longRedisTemplate;

    @Autowired
    private SessionStoreService sessionStoreService;

    @Test
    @DisplayName("로그인 시도 횟수 알 수 있다.")
    void when_get_login_try_count_then_count_should_be_returned() {
        longRedisTemplate.opsForValue().set("member:id:1:login:count", 5L);
        assertThat(sessionStoreService.getLoginTryCount(1L)).isGreaterThan(5);
    }
}
