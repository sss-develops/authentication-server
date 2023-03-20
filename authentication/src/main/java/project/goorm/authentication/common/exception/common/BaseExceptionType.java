package project.goorm.authentication.common.exception.common;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    int getCode();

    String getMessage();

    HttpStatus getStatus();

}
