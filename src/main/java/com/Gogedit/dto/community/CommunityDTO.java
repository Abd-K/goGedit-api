package com.Gogedit.dto.community;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommunityDTO {
    private String name;
    private String description;
    private Long postCount;
}

