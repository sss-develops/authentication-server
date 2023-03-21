package project.goorm.authentication.business.web.member.application;

import project.goorm.authentication.common.annotation.helper.ApplicationLayer;

@ApplicationLayer
public interface LogoutCommand {

    void logout(Long memberId, String session);

    void logoutAllDevices(Long memberId);

}
