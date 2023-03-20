package project.goorm.authentication.business.core.domain.member.entity.values;

import project.goorm.authentication.business.core.domain.common.error.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class LoginPassword {

    /**
     * 최소 8자리 이상, 대/소문자, 숫자 및 특수문자 각 1개 이상 포함
     * ex) Dkssudgktpdy1!
     */
    private static final Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");

    private String loginPassword;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected LoginPassword() {
    }

    public LoginPassword(String loginPassword) {
        validatePassword(loginPassword);
        this.loginPassword = loginPassword;
    }

    private void validatePassword(String loginPassword) {
        if (loginPassword == null) {
            throw new IllegalArgumentException("패스워드를 입력해주세요.", ErrorField.of("LoginPassword", loginPassword));
        }
        if (loginPassword.length() > 40) {
            throw new IllegalArgumentException("입력 가능한 패스워드 최대길이를 초과했습니다.", ErrorField.of("LoginPassword", loginPassword));
        }
        if (!pattern.matcher(loginPassword).matches()) {
            throw new IllegalArgumentException("올바른 비밀번호를 입력해주세요.", ErrorField.of("LoginPassword", loginPassword));
        }
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginPassword that)) return false;
        return getLoginPassword().equals(that.getLoginPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoginPassword());
    }

    @Override
    public String toString() {
        return loginPassword;
    }
}

