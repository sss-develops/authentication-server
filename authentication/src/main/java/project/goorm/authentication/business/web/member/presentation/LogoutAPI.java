package project.goorm.authentication.business.web.member.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.authentication.business.core.domain.member.entity.AuthenticatedMember;
import project.goorm.authentication.business.web.member.application.LogoutCommand;
import project.goorm.authentication.common.annotation.business.LoginMember;
import project.goorm.authentication.common.annotation.business.LoginOnly;
import project.goorm.authentication.common.annotation.helper.PresentationLayer;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@PresentationLayer
@RequestMapping(path = "/api/members/logout")
public class LogoutAPI {

    private final LogoutCommand logoutCommand;
    private final CookieUtils cookieUtils;

    public LogoutAPI(
            LogoutCommand logoutCommand,
            CookieUtils cookieUtils
    ) {
        this.logoutCommand = logoutCommand;
        this.cookieUtils = cookieUtils;
    }

    @LoginOnly
    @GetMapping(path = "/single-device")
    public ResponseEntity<Void> logoutSingleDevice(
            @LoginMember AuthenticatedMember authenticatedMember
    ) {
        logoutCommand.logout(
                authenticatedMember.getMemberId(),
                authenticatedMember.getSession()
        );
        return ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .header(SET_COOKIE, cookieUtils.createExpiredCookie())
                .build();
    }

    @LoginOnly
    @GetMapping(path = "/all-devices")
    public ResponseEntity<Void> logoutAllDevices(
            @LoginMember AuthenticatedMember authenticatedMember
    ) {
        logoutCommand.logoutAllDevices(
                authenticatedMember.getMemberId()
        );
        return ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .header(SET_COOKIE, cookieUtils.createExpiredCookie())
                .build();
    }
}
