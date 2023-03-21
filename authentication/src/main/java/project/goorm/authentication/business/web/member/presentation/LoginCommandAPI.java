package project.goorm.authentication.business.web.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.web.member.application.LoginCommand;
import project.goorm.authentication.business.web.member.presentation.request.LoginRequest;
import project.goorm.authentication.common.annotation.helper.PresentationLayer;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.PERMANENT_REDIRECT;

@PresentationLayer
@RequestMapping(path = "/api/members/login")
public class LoginCommandAPI {

    private final LoginCommand loginCommand;
    private final CookieUtils cookieUtils;

    public LoginCommandAPI(
            LoginCommand loginCommand,
            CookieUtils cookieUtils
    ) {
        this.loginCommand = loginCommand;
        this.cookieUtils = cookieUtils;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        String sessionId = loginCommand.login(
                LoginId.from(request.getLoginId()),
                LoginPassword.from(request.getLoginPassword())
        );
        return ResponseEntity
                .status(PERMANENT_REDIRECT)
                .header(SET_COOKIE, cookieUtils.createCookie(sessionId))
                .build();
    }
}
