package com.Gogedit.dto;

import com.Gogedit.dto.post.PostDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommunityDTO {
    private String name;
    private String description;
    private List<PostDTO> posts = new ArrayList<>();
}

