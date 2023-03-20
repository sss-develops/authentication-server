package project.goorm.authentication.common.exception;

import org.springframework.boot.actuate.endpoint.invoke.MissingParametersException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.goorm.authentication.common.exception.common.ErrorResponse;
import project.goorm.authentication.common.exception.common.SSSTeamException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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

}
