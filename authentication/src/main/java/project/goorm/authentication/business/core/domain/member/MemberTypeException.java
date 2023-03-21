package project.goorm.authentication.business.core.domain.member;

import org.springframework.http.HttpStatus;
import project.goorm.authentication.common.exception.common.BaseExceptionType;

public enum MemberTypeException implements BaseExceptionType {


    INVALID_LOGIN_ID_EXCEPTION(400, "아이디가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_EXCEPTION(400, "잘못된 패스워드 입니다.", HttpStatus.BAD_REQUEST),
    FORBIDDEN_ACCESS(403, "접근이 금지되었습니다.", HttpStatus.FORBIDDEN),
    ALREADY_LOGINED_EXCEPTION(403, "이미 로그인 되어 있습니다.", HttpStatus.FORBIDDEN),
    MEMBER_NOT_FOUND_EXCEPTION(404, "회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus status;

    MemberTypeException(
            int code,
            String message,
            HttpStatus status
    ) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
