package project.goorm.authentication.test.integrationtest.member.signup;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.business.web.member.application.SignupCommand;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getLoginId;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getNicknameAsValue;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getPassword;

@DisplayName("회원가입 통합 테스트")
class MemberSignupIntegrationTest extends IntegrationTestBase {

    @Autowired
    private SignupCommand signupCommand;

    @Test
    @DisplayName("올바른 정보를 입력하면 회원가입이 된다.")
    void when_valid_information_inputted_then_signup_should_be_success() {
        Long memberId = signupCommand.signup(getLoginId(), getPassword(), getNicknameAsValue());

        assertNotNull(memberId);
    }
}
