package project.goorm.authentication.common.exception;

import org.springframework.boot.actuate.endpoint.invoke.MissingParametersException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.goorm.authentication.common.exception.common.ErrorResponse;
import project.goorm.authentication.common.exception.common.LoginForbiddenException;
import project.goorm.authentication.common.exception.common.SSSTeamException;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static project.goorm.authentication.business.web.member.presentation.CookieUtils.createInvalidSessionCookie;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse catchIllegalArgumentException(IllegalArgumentException exception) {
        return ErrorResponse.of(BAD_REQUEST, exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingParametersException.class)
    public ErrorResponse catchMissingParametersException(MissingParametersException exception) {
        return ErrorResponse.of(BAD_REQUEST, exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SSSTeamException.class)
    public ErrorResponse catchSSSTeamException(SSSTeamException exception) {
        return ErrorResponse.of(exception);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(LoginForbiddenException.class)
    public ResponseEntity<ErrorResponse> catchLoginForbiddenException(LoginForbiddenException exception) {
        return ResponseEntity.status(403)
                .header(SET_COOKIE, createInvalidSessionCookie())
                .body(ErrorResponse.of(exception));
    }
}
