package project.goorm.authentication.test.helper.sut;

import project.goorm.authentication.business.core.domain.common.deleted.Deleted;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MemberDocumentSut {

    private final MemberDocument memberDocument;

    public MemberDocumentSut(MemberDocument memberDocument) {
        this.memberDocument = memberDocument;
    }

    public MemberDocumentSut shouldExist() {
        assertNotNull(memberDocument.get_id());
        return this;
    }

    public MemberDocumentSut withMemberId() {
        assertNotNull(memberDocument.getMemberId());
        return this;
    }

    public MemberDocumentSut withLoginId() {
        assertNotNull(memberDocument.getLoginId());
        return this;
    }

    public MemberDocumentSut withPassword() {
        assertNotNull(memberDocument.getLoginPassword());
        return this;
    }

    public MemberDocumentSut withNickname() {
        assertNotNull(memberDocument.getNickname());
        return this;
    }

    public MemberDocumentSut withLastLoginIpAddress() {
        assertNotNull(memberDocument.getLastLoginIpAddress());
        return this;
    }

    public MemberDocumentSut withCreatedAt() {
        assertNotNull(memberDocument.getCreatedAt());
        return this;
    }

    public MemberDocumentSut withLastModifiedAt() {
        return this;
    }

    public MemberDocumentSut withDeletedFalse() {
        assertEquals(Deleted.FALSE, memberDocument.getDeleted());
        return this;
    }

    public MemberDocumentSut build() {
        return this;
    }
}
