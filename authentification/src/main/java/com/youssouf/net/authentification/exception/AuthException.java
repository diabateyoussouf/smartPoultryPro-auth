package com.youssouf.net.authentification.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthException extends RuntimeException {

    private final HttpStatus status;

    public AuthException(String message, HttpStatus status) {
        super(message);
        if (status == null) {
            throw new IllegalArgumentException("HttpStatus cannot be null");
        }
        this.status = status;
    }

}