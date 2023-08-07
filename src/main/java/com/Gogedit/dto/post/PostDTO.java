package com.Gogedit.dto.post;

import com.Gogedit.dto.CommentDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PostDTO {
    private String id;
    private String title;
    private String body;

    private String communityName;
    List<CommentDTO> comments = new ArrayList<>();
}
