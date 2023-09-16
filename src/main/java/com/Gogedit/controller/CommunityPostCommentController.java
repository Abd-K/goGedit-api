package com.Gogedit.controller;

import com.Gogedit.converter.CommentToDTOConverter;
import com.Gogedit.dto.comment.CommentDTO;
import com.Gogedit.dto.comment.CreateCommentDTO;
import com.Gogedit.service.CommentService;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communities/{communityName}/posts/{postId}/comments")
public class CommunityPostCommentController {

  private final CommentService commentService;

  public CommunityPostCommentController(CommentService commentService) {
    this.commentService = commentService;
  }

    @PostMapping("/{commentId}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO createReply(
        @PathVariable String communityName,
        @PathVariable String postId,
        @PathVariable String commentId,
        @RequestBody CreateCommentDTO createCommentDTO) {
      return commentService.createReply(communityName, postId, commentId, createCommentDTO);
    }



  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Set<CommentDTO> getCommentsByPostId(@PathVariable String postId) {
    return CommentToDTOConverter.toDTOSet(commentService.getCommentsByPostId(postId));
  }
}

