package com.Gogedit.dto.comment;

import java.time.LocalDateTime;
import java.util.Set;

public record CommentDTO(
    String id,
    String text,
    String authorUsername,
    String postId,
    Set<CommentDTO> replies,
    LocalDateTime createdDate) {}
