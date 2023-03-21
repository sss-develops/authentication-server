package project.goorm.authentication.business.web.client.redis;

import project.goorm.authentication.common.annotation.helper.ApplicationLayer;

@ApplicationLayer
public interface RedisSessionService {

    String save(Long memberId);

    Long getMemberId(String sessionId);

    Long getLoginTryCount(Long memberId);

    void deleteSession(Long memberId, String session);

    void deleteAllSessions(Long memberId);

}
