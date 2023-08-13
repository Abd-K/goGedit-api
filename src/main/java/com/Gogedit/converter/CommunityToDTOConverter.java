package com.Gogedit.converter;

import com.Gogedit.dto.community.CommunityDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.persistence.entity.Community;
import java.util.List;
import java.util.stream.Collectors;

public class CommunityToDTOConverter {
  public static CommunityDTO toDTO(Community community) {
    //    modelmapper?  mapstruct?
    List<PostDTO> postDTOList =
        community.getPosts().stream().map(PostToDTOConverter::toDTO).collect(Collectors.toList());

    return new CommunityDTO(
        community.getName(), community.getDescription(), postDTOList.stream().count());
  }

  public static List<CommunityDTO> toDTOList(List<Community> communities) {
    return communities.stream().map(CommunityToDTOConverter::toDTO).collect(Collectors.toList());
  }
}
