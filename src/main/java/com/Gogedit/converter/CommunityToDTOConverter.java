package com.Gogedit.converter;

import com.Gogedit.dto.CommunityDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.persistence.entity.Community;
import java.util.List;
import java.util.stream.Collectors;

public class CommunityToDTOConverter {
  public static CommunityDTO toDTO(Community community) {
    //    modelmapper?  mapstruct?
    List<PostDTO> postDTOList = community.getPosts().stream()
            .map(post -> PostToDTOConverter.toDTO(post))
            .collect(Collectors.toList());

    return new CommunityDTO(community.getName(), community.getDescription(), postDTOList);
  }

  public static List<CommunityDTO> toDTOList(List<Community> communities) {
    return communities.stream()
            .map(CommunityToDTOConverter::toDTO)
            .collect(Collectors.toList());
  }

}
