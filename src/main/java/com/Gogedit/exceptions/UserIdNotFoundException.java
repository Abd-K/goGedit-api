package com.Gogedit.exceptions;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(String id) {
        super("user not found with id: " + id);
    }
}
