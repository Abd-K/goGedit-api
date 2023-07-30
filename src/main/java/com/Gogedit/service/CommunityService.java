package com.Gogedit.service;

import com.Gogedit.persistence.entity.Community;
import com.Gogedit.persistence.repository.CommunityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommunityService {

  private final CommunityRepository communityRepository;

  public CommunityService(CommunityRepository communityRepository) {
    this.communityRepository = communityRepository;
  }

  public Community createCommunity(Community newCommunity) {
    boolean communityExists = communityRepository.existsByName(newCommunity.getName());
    if (communityExists) throw new IllegalArgumentException("Community exists");

    return saveCommunity(newCommunity);
  }

  public List<Community> getCommunities() {
    return communityRepository.findAll();
  }

  public List<Community> getCommunitiesByName(String name) {
    return communityRepository.findAllByNameContainingIgnoreCase(name);
  }

  public Community getCommunityById(String communityId) {
    return communityRepository.findById(communityId).get();
  }

  public Community updateCommunity(String communityId, Community updatedCommunity) {
    final Community existingCommunity = getCommunityById(communityId);
    if (updatedCommunity.getDescription() != null) {
      existingCommunity.setDescription(updatedCommunity.getDescription());
    }
    return saveCommunity(existingCommunity);
  }

  private Community saveCommunity(Community newCommunity) {
    return communityRepository.save(newCommunity);
  }

}
