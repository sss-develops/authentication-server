package project.goorm.authentication.common.exception.common;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime eventTime;
    private final int code;
    private final String message;
    private final HttpStatus status;

    private ErrorResponse(SSSTeamException exception) {
        this.eventTime = LocalDateTime.now();
        this.code = exception.getCode();
        this.message = exception.getMessage();
        this.status = exception.getStatus();
    }

    private ErrorResponse(
            HttpStatus status,
            String message
    ) {
        this.eventTime = LocalDateTime.now();
        this.code = status.value();
        this.message = message;
        this.status = status;
    }

    public static ErrorResponse of(SSSTeamException exception) {
        return new ErrorResponse(exception);
    }

    public static ErrorResponse of(
            HttpStatus status,
            String message
    ) {
        return new ErrorResponse(status, message);
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("Time: %s, Code: %s, Message: %s, Status: %s", eventTime, code, message, status);
    }
}
