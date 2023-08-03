package com.Gogedit.service;

import com.Gogedit.dto.post.CreatePostDto;
import com.Gogedit.persistence.entity.Community;
import com.Gogedit.persistence.entity.Post;
import com.Gogedit.persistence.repository.CommunityRepository;
import com.Gogedit.persistence.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostService {

  private final PostRepository postRepository;
  private final CommunityRepository communityRepository;

  public PostService(PostRepository postRepository, CommunityRepository communityRepository) {
    this.postRepository = postRepository;
    this.communityRepository = communityRepository;
  }

  public Post createPost(String communityName, CreatePostDto createPostDto) {
    Community community = communityRepository.findCommunityByName(communityName);

    Post post = new Post();
    post.setCommunity(community);
    post.setTitle(createPostDto.getTitle());

    if (!createPostDto.getBody().trim().isEmpty()) {
      post.setBody(createPostDto.getBody());
    }

    return postRepository.save(post);
  }
}
