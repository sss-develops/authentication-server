package project.goorm.authentication.business.web.member.application.service;

import org.springframework.stereotype.Service;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;
import project.goorm.authentication.business.web.member.application.LogoutCommand;

@Service
public class LogoutService implements LogoutCommand {

    private final RedisSessionService redisSessionService;

    public LogoutService(RedisSessionService redisSessionService) {
        this.redisSessionService = redisSessionService;
    }

    @Override
    public void logout(
            Long memberId,
            String session
    ) {
        redisSessionService.deleteSession(memberId, session);
    }

    @Override
    public void logoutAllDevices(Long memberId) {
        redisSessionService.deleteAllSessions(memberId);
    }
}
