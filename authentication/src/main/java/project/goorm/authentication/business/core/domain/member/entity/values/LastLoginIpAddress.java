package project.goorm.authentication.business.core.domain.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LastLoginIpAddress {

    private String lastLoginIpAddress;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected LastLoginIpAddress() {
    }

    public LastLoginIpAddress(String lastLoginIpAddress) {
        if (lastLoginIpAddress == null || lastLoginIpAddress.isBlank()) {
            this.lastLoginIpAddress = "UN_KNOWN";
            return;
        }
        this.lastLoginIpAddress = lastLoginIpAddress;
    }

    public String getLastLoginIpAddress() {
        return lastLoginIpAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastLoginIpAddress nickName)) return false;
        return getLastLoginIpAddress().equals(nickName.getLastLoginIpAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastLoginIpAddress());
    }

    @Override
    public String toString() {
        return lastLoginIpAddress;
    }
}
