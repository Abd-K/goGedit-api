package com.Gogedit.service;

import com.Gogedit.dto.CreateCommunityDTO;
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

  public Community createCommunity(CreateCommunityDTO createCommunityDTO) {
    boolean communityExists = communityRepository.existsByName(createCommunityDTO.getName());
    if (communityExists) throw new IllegalArgumentException("Community exists");

    Community community = new Community();
    community.setName(createCommunityDTO.getName());
    community.setDescription(createCommunityDTO.getDescription());
    return saveCommunity(community);
  }

  public List<Community> getCommunities() {
    return communityRepository.findAll();
  }

  public List<Community> getCommunitiesByName(String name) {
    return communityRepository.findAllByNameContainingIgnoreCase(name);
  }

  public Community getCommunityByName(String communityName) {
    return communityRepository.findCommunityByName(communityName);
  }

  public Community updateCommunity(String communityId, Community updatedCommunity) {
    final Community existingCommunity = getCommunityByName(communityId);
    if(updatedCommunity.getDescription() != null) {
      existingCommunity.setDescription(updatedCommunity.getDescription());
    }
    return saveCommunity(existingCommunity);
  }

  private Community saveCommunity(Community newCommunity) {
    return communityRepository.save(newCommunity);
  }

}
