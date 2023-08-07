package com.Gogedit.converter;

import com.Gogedit.dto.CommentDTO;
import com.Gogedit.persistence.entity.Comment;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentToDTOConverter {
  public static CommentDTO toDTO(Comment comment) {
    //    modelmapper?  mapstruct?
    Set<CommentDTO> replies = toDTOSet(comment.getReplies());
    return new CommentDTO(
        comment.getId(),
        comment.getText(),
        comment.getAuthor(),
        comment.getPost().getId(),
        replies);
  }

  public static Set<CommentDTO> toDTOSet(Set<Comment> comments) {
    return comments.stream().map(CommentToDTOConverter::toDTO).collect(Collectors.toSet());
  }
}
