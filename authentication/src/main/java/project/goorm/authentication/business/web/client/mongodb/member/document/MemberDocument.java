package project.goorm.authentication.business.web.client.mongodb.member.document;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import project.goorm.authentication.business.core.domain.common.deleted.Deleted;
import project.goorm.authentication.business.core.domain.member.entity.Member;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;

@Document(collection = "member")
public class MemberDocument {

    private static final MemberDocument emptyDocument = new MemberDocument();

    @Id
    private String _id;

    @Column
    private Long memberId;

    @Column
    private String loginId;

    @Column
    private String loginPassword;

    @Column
    private String nickname;

    @Column
    private Instant createdAt;

    @Column
    private Instant lastModifiedAt;

    @Column
    private String lastLoginIpAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    private Deleted deleted;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 document 패키지 외부에서 호출하지 말 것.
     */
    private MemberDocument() {
    }

    public MemberDocument(Member member) {
        this._id = new ObjectId().toHexString();
        this.memberId = member.getMemberId();
        this.loginId = member.getLoginId();
        this.loginPassword = member.getLoginPassword();
        this.nickname = member.getNickname();
        this.createdAt = member.getCreatedAt();
        this.lastModifiedAt = member.getLastModifiedAt();
        this.lastLoginIpAddress = member.getLastLoginIpAddress();
        this.deleted = member.getDeleted();
    }

    public String get_id() {
        return _id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getNickname() {
        return nickname;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public String getLastLoginIpAddress() {
        return lastLoginIpAddress;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public static MemberDocument getEmptyDocument() {
        return emptyDocument;
    }

    public boolean isEmpty() {
        return _id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDocument that)) return false;
        return getMemberId().equals(that.getMemberId());
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
