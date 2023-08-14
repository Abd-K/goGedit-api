package com.Gogedit.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("user not found with username: " + username);
    }
    public UserNotFoundException() {
        super("User not found");
    }
}
