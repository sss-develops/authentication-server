package project.goorm.authentication.test.integrationtest.client.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("레디스 세션 조회 통합 테스트")
class RedisQueryIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RedisSessionService redisSessionService;

    @Test
    @DisplayName("회원 아이디가 입력되면 세션이 생성된다.")
    void when_session_saved_then_memberid_can_find_by_session() {
        Long expected = 1L;
        String session = redisSessionService.save(1L);

        assertEquals(expected, redisSessionService.getMemberId(session));
    }

    @Test
    @DisplayName("회원 아이디가 일치하지 않으면 Null 값이 반환된다.")
    void when_session_not_exist_then_memberid_should_be_null() {
        String invalidSession = "Hello";
        redisSessionService.save(1L);

        assertNull(redisSessionService.getMemberId(invalidSession));
    }
}
