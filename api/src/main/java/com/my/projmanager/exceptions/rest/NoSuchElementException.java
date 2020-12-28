package com.my.projmanager.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException() {
        super();
    }

    public NoSuchElementException(String message, Throwable cause){
        super(message, cause);
    }

    public NoSuchElementException(Throwable cause) {
        super(cause);
    }
}
