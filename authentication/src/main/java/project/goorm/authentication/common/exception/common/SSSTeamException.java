package project.goorm.authentication.common.exception.common;

import org.springframework.http.HttpStatus;

public class SSSTeamException extends RuntimeException {

    private final BaseExceptionType baseExceptionType;

    private SSSTeamException(BaseExceptionType baseExceptionType) {
        super(baseExceptionType.getMessage());
        this.baseExceptionType = baseExceptionType;
    }

    public static SSSTeamException of(BaseExceptionType baseExceptionType) {
        return new SSSTeamException(baseExceptionType);
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
