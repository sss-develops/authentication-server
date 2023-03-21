package project.goorm.authentication.business.web.member.application;

import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;

public interface LoginCommand {

    String login(LoginId loginId, LoginPassword loginPassword);

}
