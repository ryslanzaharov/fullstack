package com.auth.authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int userId) {
        super("could not find user '" + userId + "'.");
    }

    public UserNotFoundException() {
        super("could not find user .");
    }
}
