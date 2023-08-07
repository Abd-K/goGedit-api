package com.Gogedit.exceptions;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String postId) {
        super("Post not found with id: " + postId);
    }
}

