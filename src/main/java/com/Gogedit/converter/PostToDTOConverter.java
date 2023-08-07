package com.Gogedit.converter;

import com.Gogedit.dto.CommentDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.dto.post.PostSummaryDTO;
import com.Gogedit.persistence.entity.Post;
import java.util.List;
import java.util.stream.Collectors;

public class PostToDTOConverter {

  public static PostDTO toDTO(Post post) {
    //    modelmapper?  mapstruct?
    List<CommentDTO> postComments = post.getComments().stream()
            .map(comment -> CommentToDTOConverter.toDTO(comment))
            .collect(Collectors.toList());

    return new PostDTO(
        post.getId(),
        post.getTitle(),
        post.getBody(),
        post.getCommunity().getName(),
        postComments
    );
  }

  public static PostSummaryDTO toSummaryDTO(Post post) {
    //    modelmapper?  mapstruct?
    List<CommentDTO> postComments = post.getComments().stream()
            .map(comment -> CommentToDTOConverter.toDTO(comment))
            .collect(Collectors.toList());

    return new PostSummaryDTO(
            post.getId(),
            post.getTitle(),
            post.getBody(),
            post.getCommunity().getName(),
            postComments.size()
    );
  }

  public static List<PostDTO> toDTOList(List<Post> posts) {

    return posts.stream()
            .map(PostToDTOConverter::toDTO)
            .collect(Collectors.toList());
  }

  public static List<PostSummaryDTO> toSummaryDTOList(List<Post> posts) {

    return posts.stream()
            .map(PostToDTOConverter::toSummaryDTO)
            .collect(Collectors.toList());
  }
}
