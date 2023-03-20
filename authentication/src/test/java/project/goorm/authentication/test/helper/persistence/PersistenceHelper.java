package project.goorm.authentication.test.helper.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.infrastructure.command.MemberJpaRepository;
import project.goorm.authentication.business.web.client.mongodb.member.MemberDocumentRepository;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;

@Component
public class PersistenceHelper {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private MemberDocumentRepository documentRepository;

    public Member persist(Member member) {
        return memberJpaRepository.save(member);
    }

    public MemberDocument persist(MemberDocument memberDocument) {
        return documentRepository.save(memberDocument);
    }
}
