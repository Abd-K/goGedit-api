package com.Gogedit.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PostSummaryDTO {
    private String id;
    private String title;
    private String body;
    private String communityName;
    private Integer commentCount;
}
