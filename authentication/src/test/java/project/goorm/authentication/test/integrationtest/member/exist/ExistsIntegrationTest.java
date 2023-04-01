package project.goorm.authentication.test.integrationtest.member.exist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.web.member.application.service.MemberExistsService;
import project.goorm.authentication.test.helper.fixture.MemberFixture;
import project.goorm.authentication.test.helper.persistence.PersistenceHelper;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static org.assertj.core.api.Assertions.*;

@DisplayName("memberId로 존재 여부 테스트")
public class ExistsIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MemberExistsService memberExistsService;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("회원가입한 멤버의 memberId로 존재하면 true를 반환한다.")
    public void given_saved_memberId_when_find_by_member_id_then_return_true() {
        // given - precondition or setup
        Member member = persistenceHelper.persist(MemberFixture.createMember());

        // when - action or the behaviour that we are going test
        Boolean exists = memberExistsService.memberExistsByMemberId(member.getMemberId());

        // then - verify the output
        assertThat(exists).isEqualTo(true);
    }

    // JUnit test for
    @Test
    @DisplayName("존재하지 않는 회원을 조회하면 false를 반환한다.")
    public void when_find_by_member_id_then_return_false() {
        // when - action or the behaviour that we are going test
        Boolean exists = memberExistsService.memberExistsByMemberId(5L);

        // then - verify the output
        assertThat(exists).isEqualTo(false);
    }
}
