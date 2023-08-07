package com.Gogedit.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentDTO {
    private String id;
    private String text;
    private String author;
    private String postId;
    private Set<CommentDTO> replies;
}
