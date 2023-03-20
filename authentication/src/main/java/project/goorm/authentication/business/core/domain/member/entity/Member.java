package project.goorm.authentication.business.core.domain.member.entity;

import project.goorm.authentication.business.core.domain.common.date.CreatedAt;
import project.goorm.authentication.business.core.domain.common.date.LastModifiedAt;
import project.goorm.authentication.business.core.domain.common.deleted.Deleted;
import project.goorm.authentication.business.core.domain.member.entity.values.LastLoginIpAddress;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Embedded
    private LoginId loginId;

    @Embedded
    private LoginPassword loginPassword;

    @Embedded
    private Nickname nickname;

    @Embedded
    private CreatedAt createdAt;

    @Embedded
    private LastModifiedAt lastModifiedAt;

    @Embedded
    private LastLoginIpAddress lastLoginIpAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    private Deleted deleted;

    public Long getMemberId() {
        return memberId;
    }

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected Member() {
    }

    public Member(
            LoginId loginId,
            LoginPassword loginPassword,
            Nickname nickname,
            LastLoginIpAddress lastLoginIpAddress
    ) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.nickname = nickname;
        this.lastLoginIpAddress = lastLoginIpAddress;
        this.createdAt = CreatedAt.initCreatedAt();
        this.lastModifiedAt = LastModifiedAt.initLastModifiedAt();
        this.deleted = Deleted.FALSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return getMemberId().equals(member.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId());
    }

    @Override
    public String toString() {
        return memberId.toString();
    }
}
