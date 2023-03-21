package project.goorm.authentication.test.integrationtest.member.logout;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;
import project.goorm.authentication.business.web.member.application.LogoutCommand;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static com.mongodb.assertions.Assertions.assertTrue;

@DisplayName("로그아웃 통합 테스트")
class LogoutIntegrationTest extends IntegrationTestBase {

    @Autowired
    private LogoutCommand logoutCommand;

    @Autowired
    private RedisSessionService redisSessionService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName("로그아웃을 하면 해당 세션이 삭제된다.")
    void when_logout_then_session_should_be_deleted() {
        String session = redisSessionService.save(1L);

        logoutCommand.logout(1L, session);

        assertTrue(redisTemplate.opsForSet().members("member:id:1:sessions").isEmpty());
    }

    @Test
    @DisplayName("모든 기기에서 로그아웃을 하면 모든 세션이 삭제된다.")
    void when_logout_all_devices_then_all_session_should_be_deleted() {
        redisSessionService.save(1L);

        logoutCommand.logoutAllDevices(1L);

        assertTrue(redisTemplate.opsForSet().members("member:id:1:sessions").isEmpty());
    }
}
