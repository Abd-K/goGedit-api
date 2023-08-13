package com.Gogedit.service;

import com.Gogedit.converter.CommunityToDTOConverter;
import com.Gogedit.dto.community.CommunitySummaryDTO;
import com.Gogedit.dto.community.CreateCommunityDTO;
import com.Gogedit.exceptions.CommunityNotFoundException;
import com.Gogedit.persistence.entity.Community;
import com.Gogedit.persistence.repository.CommunityRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommunityService {

  private final CommunityRepository communityRepository;

  public CommunityService(CommunityRepository communityRepository) {
    this.communityRepository = communityRepository;
  }

  public CommunitySummaryDTO createCommunity(CreateCommunityDTO createCommunityDTO) {
    boolean communityExists = communityRepository.existsByName(createCommunityDTO.getName());
    if (communityExists) throw new IllegalArgumentException("Community exists");

    Community community =
        new Community(createCommunityDTO.getName(), createCommunityDTO.getDescription());
    return CommunityToDTOConverter.toDTO(saveCommunity(community));
  }

  public List<CommunitySummaryDTO> getCommunities() {
    return communityRepository.findAllCommunitiesWithPostCounts();
  }

  public List<CommunitySummaryDTO> searchCommunitiesByKeyword(String name) {
    return communityRepository.findAllByNameContainingIgnoreCase(name);
  }

  public Community getCommunityByName(String communityName) {
    return communityRepository
        .findCommunityByName(communityName)
        .orElseThrow(() -> new CommunityNotFoundException(communityName));
  }

  public CommunitySummaryDTO getCommunitySummaryByName(String communityName) {
    return communityRepository
        .findCommunitySummaryByName(communityName)
        .orElseThrow(() -> new CommunityNotFoundException(communityName));
  }

  public CommunitySummaryDTO updateCommunity(String communityName, Community updatedCommunity) {
    final Community existingCommunity = getCommunityByName(communityName);
    if (updatedCommunity.getDescription() != null) {
      existingCommunity.setDescription(updatedCommunity.getDescription());
    }
    return CommunityToDTOConverter.toDTO(saveCommunity(existingCommunity));
  }

  private Community saveCommunity(Community newCommunity) {
    return communityRepository.save(newCommunity);
  }
}
