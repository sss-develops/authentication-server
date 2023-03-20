package project.goorm.authentication.business.core.domain.member.entity.values;

import project.goorm.authentication.business.core.domain.common.error.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class LoginId {

    private static final Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");

    private String loginId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected LoginId() {
    }

    public LoginId(String loginId) {
        validateLoginId(loginId);
        this.loginId = loginId;
    }

    private void validateLoginId(String loginId) {
        if (loginId == null) {
            throw new IllegalArgumentException("아이디를 입력해주세요.", ErrorField.of("LoginId", loginId));
        }
        if (loginId.length() > 15) {
            throw new IllegalArgumentException("입력 가능한 아이디 최대길이를 초과했습니다.", ErrorField.of("LoginId", loginId));
        }
        if (!pattern.matcher(loginId).matches()) {
            throw new IllegalArgumentException("올바른 아이디를 입력해주세요.", ErrorField.of("LoginId", loginId));
        }
    }

    public String getLoginId() {
        return loginId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginId loginId1)) return false;
        return getLoginId().equals(loginId1.getLoginId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoginId());
    }

    @Override
    public String toString() {
        return loginId;
    }
}
