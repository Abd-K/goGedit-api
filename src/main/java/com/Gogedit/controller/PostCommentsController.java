package com.Gogedit.controller;

import com.Gogedit.dto.comment.CommentDTO;
import com.Gogedit.dto.comment.CreateCommentDTO;
import com.Gogedit.service.CommentService;
import com.Gogedit.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class PostCommentsController {

  private final PostService postService;
  private final CommentService commentService;

  public PostCommentsController(PostService postService, CommentService commentService) {
    this.postService = postService;
    this.commentService = commentService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentDTO createComment(
          @PathVariable String postId,
          @RequestBody CreateCommentDTO createCommentDto,
          @RequestHeader("username") String username) {
    return commentService.createComment(postId, username, createCommentDto);
  }



}
