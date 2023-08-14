package com.Gogedit.dto.community;

import java.time.LocalDateTime;

public record CommunitySummaryDTO(
    String name, String description, long postCount, LocalDateTime createdDate) {}
