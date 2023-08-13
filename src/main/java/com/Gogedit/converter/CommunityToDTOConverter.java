package com.Gogedit.converter;

import com.Gogedit.dto.community.CommunitySummaryDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.persistence.entity.Community;
import java.util.List;
import java.util.stream.Collectors;

public class CommunityToDTOConverter {
  public static CommunitySummaryDTO toDTO(Community community) {
    List<PostDTO> postDTOList =
        community.getPosts().stream().map(PostToDTOConverter::toDTO).collect(Collectors.toList());

    return new CommunitySummaryDTO(
        community.getName(),
        community.getDescription(),
        postDTOList.stream().count(),
        community.getCreatedDate());
  }
}
