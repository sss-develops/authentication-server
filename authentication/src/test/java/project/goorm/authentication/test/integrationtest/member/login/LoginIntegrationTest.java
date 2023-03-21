package project.goorm.authentication.test.integrationtest.member.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.web.member.application.SignupCommand;
import project.goorm.authentication.business.web.member.application.LoginCommand;
import project.goorm.authentication.common.exception.common.SSSTeamException;
import project.goorm.authentication.test.integrationtest.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getLoginId;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getNicknameAsValue;
import static project.goorm.authentication.test.helper.fixture.MemberFixture.getPassword;

@DisplayName("로그인 통합 테스트")
class LoginIntegrationTest extends IntegrationTestBase {

    @Autowired
    private LoginCommand loginCommand;

    @Autowired
    private SignupCommand signupCommand;

    @Test
    @DisplayName("회원이 존재하고 올바른 정보가 입력되면 로그인이 성공한다.")
    void when_member_exist_and_inputted_valid_login_information_then_login_should_be_success() {
        signupCommand.signup(getLoginId(), getPassword(), getNicknameAsValue());
        String sessionId = loginCommand.login(getLoginId(), getPassword());

        assertNotNull(sessionId);
    }

    @Test
    @DisplayName("회원이 존재하지 않는다면 SSSTeamException이 발생한다.")
    void when_member_not_exist_then_login_failed() {
        assertThatThrownBy(() -> loginCommand.login(getLoginId(), getPassword()))
                .isInstanceOf(SSSTeamException.class)
                .hasMessage("아이디가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않는다면 SSSTeamException이 발생한다.")
    void when_password_is_invalid_then_login_failed() {
        signupCommand.signup(getLoginId(), getPassword(), getNicknameAsValue());
        LoginPassword invalidPassword = LoginPassword.from("HelloWorld1!");

        assertThatThrownBy(() -> loginCommand.login(getLoginId(), invalidPassword))
                .isInstanceOf(SSSTeamException.class)
                .hasMessage("잘못된 패스워드 입니다.");
    }
}
