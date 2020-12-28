package com.my.projmanager.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException{

    public InternalServerError() {
        super();
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(Throwable cause) {
        super(cause);
    }
}
