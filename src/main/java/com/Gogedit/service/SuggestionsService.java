package com.Gogedit.service;

import com.Gogedit.converter.PostToDTOConverter;
import com.Gogedit.dto.post.PostSummaryDTO;
import com.Gogedit.persistence.repository.PostRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SuggestionsService {

  private final PostRepository postRepository;

  public SuggestionsService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<PostSummaryDTO> getSuggestedPosts() {
    return PostToDTOConverter.toSummaryDTOList(postRepository.findAllByOrderByCreatedDateDesc());
  }
}
