package project.goorm.authentication.test.integrationtest.member.nicknamecheck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.infrastructure.query.MemberQueryRepository;
import project.goorm.authentication.business.web.client.mongodb.member.MemberDocumentService;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;
import project.goorm.authentication.business.web.member.application.SignupQuery;
import project.goorm.authentication.business.web.member.application.service.SignupQueryService;
import project.goorm.authentication.test.helper.persistence.PersistenceHelper;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument.getEmptyDocument;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.createMember;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getNickname;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getNicknameAsValue;

@DisplayName("")
class NicknameSearchCountIntegrationTest extends IntegrationTestBase {

    private SignupQuery signupQuery;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @SpyBean
    private MemberQueryRepository memberQueryRepository;

    @SpyBean
    private MemberDocumentService memberDocumentService;

    private Member member;

    @BeforeEach
    void setUP() {
        this.signupQuery = new SignupQueryService(memberQueryRepository, memberDocumentService);
        member = persistenceHelper.persist(createMember());
    }

    @Test
    @DisplayName("회원 문서가 존재한다면 RDB를 호출하지 않는다.")
    void when_member_document_exist_in_mongodb_then_rdb_should_not_be_called() {
        MemberDocument document = persistenceHelper.persist(new MemberDocument(member));
        doReturn(document)
                .when(memberDocumentService).findMemberByNickname(document.getNickname());

        signupQuery.validateDuplicatedNickname(getNicknameAsValue());

        verify(memberQueryRepository, times(0)).findMemberByNickname(getNicknameAsValue());
    }

    @Test
    @DisplayName("회원 문서가 존재하지 않는다면 MongoDB를 호출한다.")
    void when_member_document_not_exist_in_mongodb_then_rdb_should_be_called() {
        doReturn(getEmptyDocument())
                .when(memberDocumentService).findMemberByNickname(getNickname());
        doReturn(Optional.ofNullable(this.member))
                .when(memberQueryRepository).findMemberByNickname(getNicknameAsValue());

        signupQuery.validateDuplicatedNickname(getNicknameAsValue());

        verify(memberQueryRepository, times(1)).findMemberByNickname(getNicknameAsValue());
    }

}
