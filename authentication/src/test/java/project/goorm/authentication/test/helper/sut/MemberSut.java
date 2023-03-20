package project.goorm.authentication.test.helper.sut;

import project.goorm.authentication.business.core.domain.member.entity.Member;

import static com.mongodb.assertions.Assertions.assertNotNull;

public class MemberSut {

    private final Member member;

    public MemberSut(Member member) {
        this.member = member;
    }

    public MemberSut shouldExist() {
        assertNotNull(member);
        return this;
    }

    public MemberSut withId() {
        assertNotNull(member.getMemberId());
        return this;
    }

    public MemberSut withNickname() {
        assertNotNull(member.getNickname());
        assertNotNull(member.getNicknameAsValue());
        return this;
    }

    public MemberSut withLoginId() {
        assertNotNull(member.getLoginId());
        assertNotNull(member.getLoginIdAsValue());
        return this;
    }

    public MemberSut withPassword() {
        assertNotNull(member.getLoginPassword());
        assertNotNull(member.getLoginPasswordAsValue());
        return this;
    }

    public MemberSut withCreatedAt() {
        assertNotNull(member.getCreatedAt());
        return this;
    }

    public MemberSut withLastModifiedAt() {
        assertNotNull(member.getLastModifiedAt());
        return this;
    }

    public MemberSut withLastLoginIpAddress() {
        assertNotNull(member.getLastLoginIpAddress());
        return this;
    }

    public MemberSut withDeletedFalse() {
        assertNotNull(member.getDeleted());
        return this;
    }

    public MemberSut build() {
        return this;
    }
}
