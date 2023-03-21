package project.goorm.authentication.test.integrationtest.client.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("레디스 세션 저장 통합 테스트")
class RedisCommandIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RedisSessionService redisSessionService;

    @Test
    @DisplayName("회원 아이디가 입력되면 세션이 생성된다.")
    void when_memberid_inputted_then_session_should_be_created() {
        String session = redisSessionService.save(1L);

        assertNotNull(session);
    }
}
