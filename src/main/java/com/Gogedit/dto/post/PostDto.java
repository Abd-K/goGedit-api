package com.Gogedit.dto.post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto {
    private String id;
    private String title;
    private String body;
    private String communityName;
}
