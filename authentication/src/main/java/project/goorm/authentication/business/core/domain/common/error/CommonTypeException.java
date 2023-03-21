package project.goorm.authentication.business.core.domain.common.error;

import org.springframework.http.HttpStatus;
import project.goorm.authentication.common.exception.common.BaseExceptionType;

public enum CommonTypeException implements BaseExceptionType {

    NUMBER_FORMAT_EXCEPTION(400, "회원 아이디가 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    REDIS_WRONG_TYPE_DATASTRUCTURE_EXCEPTION(502, "올바르지 않은 키를 입력했습니다.", HttpStatus.BAD_GATEWAY),
    REDIS_CONNECTION_FAILURE_EXCEPTION(502, "서버에 문제가 발생했습니다.", HttpStatus.BAD_GATEWAY);

    private final int code;
    private final String message;
    private final HttpStatus status;

    CommonTypeException(
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
