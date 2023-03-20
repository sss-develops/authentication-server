package project.goorm.authentication.business.web.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.business.core.domain.member.infrastructure.query.MemberQueryRepository;
import project.goorm.authentication.business.web.client.mongodb.member.MemberDocumentService;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;
import project.goorm.authentication.business.web.member.application.SignupQuery;
import project.goorm.authentication.business.web.member.presentation.response.DuplicatedNicknameCheckResponse;

import java.util.Optional;

import static project.goorm.authentication.business.web.member.presentation.response.DuplicatedNicknameCheckResponse.createNicknameExistResponse;
import static project.goorm.authentication.business.web.member.presentation.response.DuplicatedNicknameCheckResponse.createNicknameNotExistResponse;

@Service
public class SignupQueryService implements SignupQuery {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberDocumentService memberDocumentService;

    public SignupQueryService(
            MemberQueryRepository memberQueryRepository,
            MemberDocumentService memberDocumentService
    ) {
        this.memberQueryRepository = memberQueryRepository;
        this.memberDocumentService = memberDocumentService;
    }

    @Override
    @Transactional(readOnly = true)
    public DuplicatedNicknameCheckResponse validateDuplicatedNickname(Nickname nickname) {
        MemberDocument document = memberDocumentService.findMemberByNickname(nickname.getNickname());
        if (!document.isEmpty()) {
            return createNicknameExistResponse();
        }
        Optional<Member> member = memberQueryRepository.findMemberByNickname(nickname);
        if (member.isPresent()) {
            memberDocumentService.persist(new MemberDocument(member.get()));
            return createNicknameExistResponse();
        }
        return createNicknameNotExistResponse();
    }
}
