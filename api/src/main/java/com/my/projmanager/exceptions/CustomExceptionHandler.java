package com.my.projmanager.exceptions;

import com.my.projmanager.exceptions.rest.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetail> handleResourceNotFoundException(Exception e, WebRequest rq) {
    ErrorDetail errDetail = new ErrorDetail(new Date(), e.getMessage(), rq.getDescription(false));

    if (e instanceof ResourceNotFoundException) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errDetail);
    else if (e instanceof NoSuchElementException) return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errDetail);
    else if (e instanceof DataFormatException) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errDetail);
    else if (e instanceof InternalServerError) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errDetail);
    else return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(errDetail);
  }
}
