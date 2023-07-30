package com.Gogedit.dto.post;

import lombok.Getter;

@Getter
public class CreatePostDto {
    private String title;
    private String body;
//    TODO: remove and replace with header?
    private String author;
}

