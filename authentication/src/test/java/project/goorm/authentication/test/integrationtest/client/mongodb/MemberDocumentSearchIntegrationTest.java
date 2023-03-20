package project.goorm.authentication.test.integrationtest.client.mongodb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.web.client.mongodb.member.MemberDocumentService;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;
import project.goorm.authentication.test.helper.persistence.PersistenceHelper;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.createMember;

@DisplayName("회원 문서 조회 통합 테스트")
class MemberDocumentSearchIntegrationTest extends IntegrationTestBase {

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Autowired
    private MemberDocumentService memberDocumentService;

    @Test
    @DisplayName("회원이 존재하면 닉네임으로 회원문서를 조회할 수 있다.")
    void when_member_exist_then_memberdocument_should_be_persisted() {
        Member member = persistenceHelper.persist(createMember());
        memberDocumentService.persist(new MemberDocument(member));

        MemberDocument document = memberDocumentService.findMemberByNickname(member.getNickname());

        assertNotNull(document);
    }
}
