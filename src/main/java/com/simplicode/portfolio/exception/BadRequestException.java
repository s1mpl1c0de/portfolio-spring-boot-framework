package com.simplicode.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final String MESSAGE = "An error occurred, please contact the administrator.";

    public BadRequestException() {
        super(MESSAGE);
    }

}
