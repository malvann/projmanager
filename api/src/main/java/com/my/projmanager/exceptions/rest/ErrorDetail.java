package com.my.projmanager.exceptions.rest;

/*
 * A sample class for adding error information in the response
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorDetail {
    private Date timeStamp;
    private String message;
    private String detail;
}
