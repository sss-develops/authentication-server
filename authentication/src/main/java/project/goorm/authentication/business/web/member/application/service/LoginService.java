package project.goorm.authentication.business.web.member.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.core.domain.member.infrastructure.query.MemberQueryRepository;
import project.goorm.authentication.business.web.client.redis.RedisSessionService;
import project.goorm.authentication.business.web.member.application.LoginCommand;
import project.goorm.authentication.common.exception.common.SSSTeamException;

import static project.goorm.authentication.business.core.domain.member.MemberTypeException.FORBIDDEN_ACCESS;
import static project.goorm.authentication.business.core.domain.member.MemberTypeException.INVALID_LOGIN_ID_EXCEPTION;
import static project.goorm.authentication.business.core.domain.member.MemberTypeException.INVALID_PASSWORD_EXCEPTION;

@Service
public class LoginService implements LoginCommand {

    private final PasswordEncoder passwordEncoder;
    private final MemberQueryRepository memberQueryRepository;
    private final RedisSessionService sessionCommand;

    public LoginService(
            PasswordEncoder passwordEncoder,
            MemberQueryRepository memberQueryRepository,
            RedisSessionService sessionCommand
    ) {
        this.passwordEncoder = passwordEncoder;
        this.memberQueryRepository = memberQueryRepository;
        this.sessionCommand = sessionCommand;
    }

    @Override
    @Transactional(readOnly = true)
    public String login(LoginId loginId, LoginPassword loginPassword) {
        Member member = memberQueryRepository.findByLoginId(loginId)
                .orElseThrow(() -> SSSTeamException.of(INVALID_LOGIN_ID_EXCEPTION));
        validatePassword(loginPassword, member);
        return sessionCommand.save(member.getMemberId());
    }

    private void validatePassword(LoginPassword loginPassword, Member member) {
        if (!passwordEncoder.matches(loginPassword.getLoginPassword(), member.getLoginPassword())) {
            Long failureCount = sessionCommand.getLoginTryCount(member.getMemberId());
            if (failureCount > 5) {
                throw SSSTeamException.of(FORBIDDEN_ACCESS);
            }
            throw SSSTeamException.of(INVALID_PASSWORD_EXCEPTION);
        }
    }
}
