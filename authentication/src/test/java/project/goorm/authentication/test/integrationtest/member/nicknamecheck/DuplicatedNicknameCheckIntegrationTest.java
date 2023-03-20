package project.goorm.authentication.test.integrationtest.member.nicknamecheck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.business.web.member.application.SignupQuery;
import project.goorm.authentication.business.web.member.presentation.response.DuplicatedNicknameCheckResponse;
import project.goorm.authentication.test.helper.persistence.PersistenceHelper;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.assertions.Assertions.assertTrue;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.createMember;

@DisplayName("중복 닉네임 체크 통합 테스트")
class DuplicatedNicknameCheckIntegrationTest extends IntegrationTestBase {

    @Autowired
    private SignupQuery signupQuery;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("중복 닉네임이 존재하면 true를 반환한다.")
    void when_duplicated_nickname_exist_result_should_be_true() {
        Member member = persistenceHelper.persist(createMember());

        DuplicatedNicknameCheckResponse response =
                signupQuery.validateDuplicatedNickname(member.getNicknameAsValue());

        assertTrue(response.exist());
    }

    @Test
    @DisplayName("중복 닉네임이 존재하지 않으면 false를 반환한다.")
    void when_duplicated_nickname_not_exist_result_should_be_false() {
        DuplicatedNicknameCheckResponse response =
                signupQuery.validateDuplicatedNickname(Nickname.from("helloworld"));

        assertFalse(response.exist());
    }
}
