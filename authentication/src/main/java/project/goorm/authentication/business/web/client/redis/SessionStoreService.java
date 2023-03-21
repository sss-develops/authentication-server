package project.goorm.authentication.business.web.client.redis;

import lombok.NonNull;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.authentication.common.exception.common.SSSTeamException;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.MINUTES;
import static project.goorm.authentication.business.core.domain.common.error.CommonTypeException.REDIS_CONNECTION_FAILURE_EXCEPTION;
import static project.goorm.authentication.business.core.domain.common.error.CommonTypeException.REDIS_WRONG_TYPE_DATASTRUCTURE_EXCEPTION;

@Service
public class SessionStoreService implements RedisSessionService {

    @Resource(name = "sessionsRedisTemplate")
    private final RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "sessionRedisTemplate")
    private final StringRedisTemplate stringRedisTemplate;

    @Resource(name = "loginCountRedisTemplate")
    private final RedisTemplate<String, Long> loginCountRedisTemplate;

    public SessionStoreService(
            RedisTemplate<String, Object> redisTemplate,
            StringRedisTemplate stringRedisTemplate,
            RedisTemplate<String, Long> loginCountRedisTemplate
    ) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.loginCountRedisTemplate = loginCountRedisTemplate;
    }

    @Override
    @Transactional
    public String save(Long memberId) {
        try {
            String session = UUID.randomUUID().toString();
            stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
                public List<Object> execute(@NonNull RedisOperations operations) throws DataAccessException {
                    operations.multi();

                    redisTemplate.opsForSet().add(getSessionsKey(memberId), session);
                    redisTemplate.expire(getSessionsKey(memberId), 20, MINUTES);
                    stringRedisTemplate.opsForValue().set(session, String.valueOf(memberId), Duration.ofMinutes(20));

                    @SuppressWarnings("unchecked")
                    List<Object> transactionCommit = operations.exec();
                    return transactionCommit;
                }
            });
            return session;
        } catch (RedisSystemException e) {
            throw SSSTeamException.of(REDIS_WRONG_TYPE_DATASTRUCTURE_EXCEPTION);
        } catch (DataAccessException e) {
            throw SSSTeamException.of(REDIS_CONNECTION_FAILURE_EXCEPTION);
        }
    }

    @Override
    public Long getMemberId(String session) {
        try {
            String memberId = stringRedisTemplate.opsForValue().get(session);
            if (memberId != null) {
                return Long.parseLong(memberId);
            }
            return null;
        } catch (RedisSystemException e) {
            throw SSSTeamException.of(REDIS_WRONG_TYPE_DATASTRUCTURE_EXCEPTION);
        }
    }

    @Override
    public Long getLoginTryCount(Long memberId) {
        try {
            Long loginTryCount = loginCountRedisTemplate.opsForValue().increment(getLoginCountKey(memberId));
            loginCountRedisTemplate.expire(getLoginCountKey(memberId), Duration.ofMinutes(30));
            return loginTryCount;
        } catch (DataAccessException e) {
            throw SSSTeamException.of(REDIS_CONNECTION_FAILURE_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public void deleteSession(Long memberId, String session) {
        try {
            stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
                public List<Object> execute(@NonNull RedisOperations operations) throws DataAccessException {
                    operations.multi();

                    stringRedisTemplate.delete(session);
                    redisTemplate.opsForSet().remove(getSessionsKey(memberId), session);

                    @SuppressWarnings("unchecked")
                    List<Object> transactionCommit = operations.exec();
                    return transactionCommit;
                }
            });
        } catch (RedisSystemException e) {
            throw SSSTeamException.of(REDIS_WRONG_TYPE_DATASTRUCTURE_EXCEPTION);
        } catch (DataAccessException e) {
            throw SSSTeamException.of(REDIS_CONNECTION_FAILURE_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public void deleteAllSessions(Long memberId) {
        try {
            stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
                public List<Object> execute(@NonNull RedisOperations operations) throws DataAccessException {
                    operations.multi();

                    redisTemplate.delete(getSessionsKey(memberId));

                    @SuppressWarnings("unchecked")
                    List<Object> transactionCommit = operations.exec();
                    return transactionCommit;
                }
            });
        } catch (RedisSystemException e) {
            throw SSSTeamException.of(REDIS_WRONG_TYPE_DATASTRUCTURE_EXCEPTION);
        } catch (DataAccessException e) {
            throw SSSTeamException.of(REDIS_CONNECTION_FAILURE_EXCEPTION);
        }
    }

    private String getSessionsKey(Long memberId) {
        return String.format("member:id:%s:sessions", memberId);
    }

    private String getLoginCountKey(Long memberId) {
        return String.format("member:id:%s:login:count", memberId);
    }
}
