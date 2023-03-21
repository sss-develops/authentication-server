package project.goorm.authentication.test.integrationtest.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import project.goorm.authentication.business.common.filter.SessionValidationFilter;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.FORBIDDEN;

class SessionFilterTest extends IntegrationTestBase {

    @Autowired
    private SessionValidationFilter filter;

    @Resource(name = "sessionRedisTemplate")
    private StringRedisTemplate redisTemplate;

    private MockHttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    @BeforeEach
    void setUP() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        chain = new MockFilterChain();
    }

    @Test
    @DisplayName("이미 로그인 된 사용자가 로그인을 시도하는 경우 403 상태코드를 반환한다.")
    void when_already_logined_member_try_login_then_statuscode_should_be_403() throws ServletException, IOException {
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(uuid, "1");
        request.setCookies(new Cookie("SESSION_ID", uuid));

        filter.doFilter(request, response, chain);

        assertEquals(FORBIDDEN.value(), response.getStatus());
    }

    @Test
    @DisplayName("로그인 되지 않은 사용자가 로그인을 시도하면 200 상태코드를 받는다.")
    void when_not_logined_member_try_login_then_statuscode_should_be_200() throws ServletException, IOException {
        filter.doFilter(request, response, chain);

        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("최대 로그인 시도 횟수를 넘은 사용자가 로그인을 시도하면 403 상태코드를 받는다.")
    void when_max_login_count_exceed_member_try_login_then_statuscode_should_be_403() throws ServletException, IOException {
        request.setCookies(new Cookie("INVALID_SESSION_ID", UUID.randomUUID().toString()));

        filter.doFilter(request, response, chain);

        assertEquals(FORBIDDEN.value(), response.getStatus());
    }
}
