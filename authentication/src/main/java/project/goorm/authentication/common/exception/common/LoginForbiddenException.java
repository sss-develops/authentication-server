package project.goorm.authentication.common.exception.common;

import org.springframework.http.HttpStatus;

public class LoginForbiddenException extends RuntimeException {

    private final BaseExceptionType baseExceptionType;

    private LoginForbiddenException(BaseExceptionType baseExceptionType) {
        super(baseExceptionType.getMessage());
        this.baseExceptionType = baseExceptionType;
    }

    public static LoginForbiddenException of(BaseExceptionType baseExceptionType) {
        return new LoginForbiddenException(baseExceptionType);
    }

    public BaseExceptionType getBaseExceptionType() {
        return baseExceptionType;
    }

    public int getCode() {
        return baseExceptionType.getCode();
    }

    public String getMessage() {
        return baseExceptionType.getMessage();
    }

    public HttpStatus getStatus() {
        return baseExceptionType.getStatus();
    }
}
