package com.giggle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by enda on 17/12/15.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorisedRequestException extends RuntimeException {
}
