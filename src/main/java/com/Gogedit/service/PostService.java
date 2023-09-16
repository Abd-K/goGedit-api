package com.Gogedit.service;

import com.Gogedit.converter.PostToDTOConverter;
import com.Gogedit.dto.post.CreatePostDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.dto.post.PostSummaryDTO;
import com.Gogedit.dto.post.UpdatePostDTO;
import com.Gogedit.exceptions.PostNotFoundException;
import com.Gogedit.persistence.entity.AppUser;
import com.Gogedit.persistence.entity.Community;
import com.Gogedit.persistence.entity.Post;
import com.Gogedit.persistence.repository.CommunityRepository;
import com.Gogedit.persistence.repository.PostRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostService {

  private final PostRepository postRepository;
  private final CommunityRepository communityRepository;
  private final CommunityService communityService;
  private final UserService userService;

  public PostService(
      PostRepository postRepository,
      CommunityRepository communityRepository,
      CommunityService communityService,
      UserService userService) {
    this.postRepository = postRepository;
    this.communityRepository = communityRepository;
    this.communityService = communityService;
    this.userService = userService;
  }

  public PostDTO createPost(String communityName, CreatePostDTO createPostDTO, String username) {
    Community community = communityService.getCommunityByName(communityName);
    AppUser author = userService.getUserByUsername(username);
    Post post = new Post(createPostDTO.title(), community, author);

    if (!createPostDTO.body().trim().isEmpty()) {
      post.setBody(createPostDTO.body());
    }

    return PostToDTOConverter.toDTO(postRepository.save(post));
  }

  public List<PostSummaryDTO> getAllPostsByCommunityName(String communityName) {

    return postRepository.findAllPostsByCommunityName(communityName);
  }

  public PostDTO getPost(String postId) {
    return PostToDTOConverter.toDTO(getPostById(postId));
  }

  public Post getPostById(String postId) {
    return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
  }

  public List<PostSummaryDTO> searchPostsByKeyword(String keyword) {
    return postRepository.findAllByTitleContainingIgnoreCase(keyword);
  }

  public List<PostSummaryDTO> getUserPosts(String username) {
    return postRepository.findAllByUsername(username);
  }

  public void deletePost(String postId) {
    postRepository.deleteById(postId);
  }

  public PostDTO updatePost(String postId, UpdatePostDTO updatePostDTO) {
    Post post = getPostById(postId);

    if (updatePostDTO.body() != null) {
      post.setBody(updatePostDTO.body());
    }

    return PostToDTOConverter.toDTO(postRepository.save(post));
  }
}
