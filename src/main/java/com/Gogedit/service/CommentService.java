package com.Gogedit.service;

import com.Gogedit.converter.CommentToDTOConverter;
import com.Gogedit.dto.CommentDTO;
import com.Gogedit.dto.CreateCommentDTO;
import com.Gogedit.exceptions.CommentNotFoundException;
import com.Gogedit.persistence.entity.Comment;
import com.Gogedit.persistence.entity.Post;
import com.Gogedit.persistence.repository.CommentRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final PostService postService;

  public CommentService(CommentRepository commentRepository, PostService postService) {
    this.commentRepository = commentRepository;
    this.postService = postService;
  }

  public CommentDTO createComment(
      String communityName, String postId, CreateCommentDTO createCommentDto) {
    Post post = postService.getPostById(postId);

    Comment comment = new Comment();
    comment.setPost(post);
    comment.setText(createCommentDto.getText());
    comment.setAuthor(createCommentDto.getAuthor());
    return CommentToDTOConverter.toDTO(commentRepository.save(comment));
  }

  public CommentDTO createReply(
      String communityName, String postId, String commentId, CreateCommentDTO createCommentDTO) {
    Comment parentComment = getCommentById(commentId);

    Comment reply = new Comment();
    reply.setParentComment(parentComment);
    reply.setPost(parentComment.getPost());
    reply.setText(createCommentDTO.getText());
    reply.setAuthor(createCommentDTO.getAuthor());

    return CommentToDTOConverter.toDTO(commentRepository.save(reply));
  }

  private Comment getCommentById(String commentId) {
    return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
  }

  public Set<Comment> getCommentsByPostId(String postId) {
    Post post = postService.getPostById(postId);

    Set<Comment> postComments = post.getComments();
      return postComments;
  }
}
