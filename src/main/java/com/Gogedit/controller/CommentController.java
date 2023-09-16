package com.Gogedit.controller;

import com.Gogedit.dto.comment.CommentDTO;
import com.Gogedit.dto.comment.UpdateCommentDTO;
import com.Gogedit.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @DeleteMapping("/{commentId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteComment(@PathVariable String commentId) {
    commentService.deleteComment(commentId);
  }

  @PatchMapping("/{commentId}")
  @ResponseStatus(HttpStatus.OK)
  public CommentDTO updatePost(@PathVariable String commentId, @RequestBody UpdateCommentDTO updateCommentDTO) {
    return commentService.patchUpdateComment(commentId, updateCommentDTO);
  }
}

