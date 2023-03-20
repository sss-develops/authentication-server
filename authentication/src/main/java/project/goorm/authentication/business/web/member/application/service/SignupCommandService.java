package project.goorm.authentication.business.web.member.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.business.core.domain.member.infrastructure.command.MemberJpaRepository;
import project.goorm.authentication.business.web.client.mongodb.member.MemberDocumentService;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;
import project.goorm.authentication.business.web.member.application.SignupCommand;

import static java.lang.Boolean.TRUE;

@Service
public class SignupCommandService implements SignupCommand {

    private final PasswordEncoder passwordEncoder;
    private final MemberJpaRepository memberRepository;
    private final MemberDocumentService memberDocumentService;

    public SignupCommandService(
            PasswordEncoder passwordEncoder,
            MemberJpaRepository memberRepository,
            MemberDocumentService memberDocumentService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.memberDocumentService = memberDocumentService;
    }

    @Override
    @Transactional
    public Long signup(
            LoginId loginId,
            LoginPassword password,
            Nickname nickname
    ) {
        Member member = memberRepository.save(createMember(loginId, password, nickname));
        memberDocumentService.persist(new MemberDocument(member));
        return member.getMemberId();
    }

    private Member createMember(
            LoginId loginId,
            LoginPassword password,
            Nickname nickname
    ) {
        String encodedPassword = passwordEncoder.encode(password.getLoginPassword());
        LoginPassword loginPassword = LoginPassword.encode(TRUE, encodedPassword);
        return new Member(loginId, loginPassword, nickname);
    }
}
