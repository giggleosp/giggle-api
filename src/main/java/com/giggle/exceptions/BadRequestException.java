package com.giggle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by enda on 15/12/15.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing parameters")
public class BadRequestException extends RuntimeException {

    public BadRequestException() {

    }
}
