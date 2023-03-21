package project.goorm.authentication.common.configuration.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.goorm.authentication.business.common.filter.SessionValidationFilter;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;

@Configuration
public class ServletConfiguration {

    private final RedisSessionService redisSessionService;
    private final ObjectMapper objectMapper;

    public ServletConfiguration(
            RedisSessionService redisSessionService,
            ObjectMapper objectMapper
    ) {
        this.redisSessionService = redisSessionService;
        this.objectMapper = objectMapper;
    }

    @Bean
    public FilterRegistrationBean<SessionValidationFilter> sessionValidationFilterFilterRegistrationBean() {
        FilterRegistrationBean<SessionValidationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SessionValidationFilter(redisSessionService, objectMapper));
        registrationBean.addUrlPatterns("/api/members/login/*");
        return registrationBean;
    }
}
