package project.goorm.authentication.business.web.client.mongodb.member;

import project.goorm.authentication.business.web.client.mongodb.member.document.MemberDocument;
import project.goorm.authentication.common.annotation.helper.ApplicationLayer;

@ApplicationLayer
public interface MemberDocumentService {

    MemberDocument persist(MemberDocument document);

    MemberDocument findMemberByNickname(String nickname);

}
