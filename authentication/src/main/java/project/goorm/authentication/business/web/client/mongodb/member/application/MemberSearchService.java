package project.goorm.authentication.business.web.client.mongodb.member.application;

import org.springframework.stereotype.Component;
import project.goorm.authentication.business.core.domain.common.deleted.Deleted;
import project.goorm.authentication.business.web.client.mongodb.member.MemberDocumentRepository;
import project.goorm.authentication.business.web.client.mongodb.member.MemberDocumentService;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;

import java.util.Optional;

@Component
public class MemberSearchService implements MemberDocumentService {

    private final MemberDocumentRepository memberDocumentRepository;

    public MemberSearchService(MemberDocumentRepository memberDocumentRepository) {
        this.memberDocumentRepository = memberDocumentRepository;
    }

    @Override
    public MemberDocument persist(MemberDocument document) {
        return memberDocumentRepository.save(document);
    }

    @Override
    public MemberDocument findMemberByNickname(String nickname) {
        Optional<MemberDocument> document = memberDocumentRepository.findMemberDocumentByNickname(nickname, Deleted.FALSE);
        return document.orElseGet(MemberDocument::getEmptyDocument);
    }
}
