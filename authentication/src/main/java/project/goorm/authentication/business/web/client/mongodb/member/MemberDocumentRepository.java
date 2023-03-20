package project.goorm.authentication.business.web.client.mongodb.member;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import project.goorm.authentication.business.core.domain.common.deleted.Deleted;
import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;
import project.goorm.authentication.common.annotation.helper.ApplicationLayer;

import java.util.Optional;

@ApplicationLayer
public interface MemberDocumentRepository extends MongoRepository<MemberDocument, String> {

    @Query("{nickname: ?0, deleted: ?1}")
    Optional<MemberDocument> findMemberDocumentByNickname(
            @Param("nickname") String nickname,
            @Param("deleted") Deleted deleted
    );
}
