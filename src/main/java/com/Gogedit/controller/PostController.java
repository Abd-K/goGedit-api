package com.Gogedit.controller;

import com.Gogedit.converter.PostToDTOConverter;
import com.Gogedit.dto.post.CreatePostDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.dto.post.CreatePostDto;
import com.Gogedit.dto.post.PostDto;
import com.Gogedit.persistence.entity.Post;
import com.Gogedit.service.PostService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communities/{communityName}/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostDTO createPost(
      @PathVariable String communityName, @RequestBody CreatePostDTO createPostDTO) {
    return postService.createPost(communityName, createPostDTO);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PostDTO> getAllPostsByCommunityName(@PathVariable String communityName) {
    return PostToDTOConverter.toDTOList(postService.getAllPostsByCommunityName(communityName));
  }

  @GetMapping("/{postId}")
  @ResponseStatus(HttpStatus.OK)
  public PostDTO getPost(@PathVariable String postId) {
    return postService.getPost(postId);
  }
}
