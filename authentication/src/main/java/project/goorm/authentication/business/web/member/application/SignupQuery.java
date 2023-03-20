package project.goorm.authentication.business.web.member.application;

import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.business.web.member.presentation.response.DuplicatedNicknameCheckResponse;
import project.goorm.authentication.common.annotation.helper.ApplicationLayer;

@ApplicationLayer
public interface SignupQuery {

    DuplicatedNicknameCheckResponse validateDuplicatedNickname(Nickname nickname);

}
