package com.Gogedit.converter;

import com.Gogedit.dto.comment.CommentDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.persistence.entity.Post;
import java.util.List;
import java.util.stream.Collectors;

public class PostToDTOConverter {

  public static PostDTO toDTO(Post post) {

    List<CommentDTO> postComments =
        post.getComments().stream().map(CommentToDTOConverter::toDTO).collect(Collectors.toList());

    return new PostDTO(
        post.getId(),
        post.getTitle(),
        post.getBody(),
        post.getCommunity().getName(),
        post.getCreatedDate(),
        postComments,
        post.getAuthor().getUsername());
  }
}
