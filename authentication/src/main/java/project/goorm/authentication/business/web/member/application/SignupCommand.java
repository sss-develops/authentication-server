package project.goorm.authentication.business.web.member.application;

import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.common.annotation.helper.ApplicationLayer;

@ApplicationLayer
public interface SignupCommand {

    Long signup(LoginId loginId, LoginPassword password, Nickname nickname);

}
