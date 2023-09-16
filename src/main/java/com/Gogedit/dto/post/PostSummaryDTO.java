package com.Gogedit.dto.post;

import java.time.LocalDateTime;

public record PostSummaryDTO(
    String id,
    String title,
    String body,
    String communityName,
    LocalDateTime createdDate,
    Integer commentCount,
    String authorUsername) {}
