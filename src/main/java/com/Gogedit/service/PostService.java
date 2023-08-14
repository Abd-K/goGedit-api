package com.Gogedit.service;

import com.Gogedit.converter.PostToDTOConverter;
import com.Gogedit.dto.post.CreatePostDTO;
import com.Gogedit.dto.post.PostDTO;
import com.Gogedit.dto.post.PostSummaryDTO;
import com.Gogedit.exceptions.PostNotFoundException;
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

  public PostService(
      PostRepository postRepository,
      CommunityRepository communityRepository,
      CommunityService communityService) {
    this.postRepository = postRepository;
    this.communityRepository = communityRepository;
    this.communityService = communityService;
  }

  public PostDTO createPost(String communityName, CreatePostDTO createPostDTO) {
    Community community = communityService.getCommunityByName(communityName);

    Post post = new Post(createPostDTO.getTitle(), community);

    if (!createPostDTO.getBody().trim().isEmpty()) {
      post.setBody(createPostDTO.getBody());
    }

    PostDTO postDTO = PostToDTOConverter.toDTO(postRepository.save(post));
    return postDTO;
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
}
