package project.goorm.authentication.business.web.member.application.service;

import org.springframework.stereotype.Service;
import project.goorm.authentication.business.core.domain.member.infrastructure.command.MemberJpaRepository;

@Service
public class MemberExistsService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberExistsService(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    public Boolean memberExistsByMemberId(Long memberId) {
        return memberJpaRepository.existsMemberByMemberId(memberId);
    }
}
