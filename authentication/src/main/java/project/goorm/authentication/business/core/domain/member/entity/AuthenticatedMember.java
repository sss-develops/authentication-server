package project.goorm.authentication.business.core.domain.member.entity;

import java.util.Objects;

public class AuthenticatedMember {

    private final Long memberId;
    private final String session;

    public AuthenticatedMember(
            Long memberId,
            String session
    ) {
        this.memberId = memberId;
        this.session = session;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getSession() {
        return session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticatedMember that)) return false;
        return getMemberId().equals(that.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId());
    }
}
