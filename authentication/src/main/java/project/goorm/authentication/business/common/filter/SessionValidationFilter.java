package project.goorm.authentication.business.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Predicate;

import static project.goorm.authentication.business.core.domain.common.error.CommonTypeException.LOGIN_FORBIDDEN_EXCEPTION;
import static project.goorm.authentication.business.core.domain.member.MemberTypeException.ALREADY_LOGINED_EXCEPTION;

@Component
public class SessionValidationFilter implements Filter {

    private static final String SESSION_ID = "SESSION_ID";
    private static final String INVALID_SESSION_ID = "INVALID_SESSION_ID";

    private final RedisSessionService sessionService;
    private final ObjectMapper objectMapper;

    public SessionValidationFilter(
            RedisSessionService sessionService,
            ObjectMapper objectMapper
    ) {
        this.sessionService = sessionService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        String invalidSessionId = getInvalidSessionId(request);
        if (invalidSessionId != null) {
            forbiddenAccess(response);
            return;
        }
        String sessionId = getSessionId(request);
        if (sessionId == null) {
            chain.doFilter(request, response);
            return;
        }
        Long memberId = sessionService.getMemberId(sessionId);
        if (memberId == null) {
            chain.doFilter(request, response);
            return;
        }
        denyAccess(response);
    }

    private String getInvalidSessionId(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            return null;
        }
        Cookie sessionCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(hasInvalidSessionId())
                .findAny()
                .orElseGet(() -> null);
        return sessionCookie == null ? null : sessionCookie.getValue();
    }

    private Predicate<Cookie> hasInvalidSessionId() {
        return cookie -> cookie.getName().equals(INVALID_SESSION_ID);
    }

    private String getSessionId(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            return null;
        }
        Cookie sessionCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(hasSessionId())
                .findAny()
                .orElseGet(() -> null);
        return sessionCookie == null ? null : sessionCookie.getValue();
    }

    private Predicate<Cookie> hasSessionId() {
        return cookie -> cookie.getName().equals(SESSION_ID);
    }

    private void denyAccess(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(ALREADY_LOGINED_EXCEPTION.getCode());
        httpServletResponse.getWriter().write(ALREADY_LOGINED_EXCEPTION.getMessage());
    }

    private void forbiddenAccess(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(LOGIN_FORBIDDEN_EXCEPTION.getCode());
        httpServletResponse.getWriter().write(LOGIN_FORBIDDEN_EXCEPTION.getMessage());
    }
}
