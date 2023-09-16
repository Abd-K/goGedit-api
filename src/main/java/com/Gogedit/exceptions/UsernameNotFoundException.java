package com.Gogedit.exceptions;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String username) {
        super("user not found with username: " + username);
    }
    public UsernameNotFoundException() {
        super("User not found");
    }
}
