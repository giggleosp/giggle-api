package com.giggle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by enda on 15/12/15.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserConflictException extends RuntimeException {
    public UserConflictException(String attributeType, String attribute) {
        super(String.format("User with %s '%s' already exists", attributeType, attribute));
    }

    public UserConflictException() {
        super("Database already contains this user.");
    }
}
