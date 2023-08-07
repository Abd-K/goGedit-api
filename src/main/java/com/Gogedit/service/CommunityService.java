package com.Gogedit.service;

import com.Gogedit.converter.CommunityToDTOConverter;
import com.Gogedit.dto.CommunityDTO;
import com.Gogedit.dto.CreateCommunityDTO;
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

  public CommunityDTO createCommunity(CreateCommunityDTO createCommunityDTO) {
    boolean communityExists = communityRepository.existsByName(createCommunityDTO.getName());
    if (communityExists) throw new IllegalArgumentException("Community exists");

    Community community = new Community();
    community.setName(createCommunityDTO.getName());
    community.setDescription(createCommunityDTO.getDescription());
    return CommunityToDTOConverter.toDTO(saveCommunity(community));
  }

  public List<CommunityDTO> getCommunities() {
    List<Community> allCommunities = communityRepository.findAll();

    return CommunityToDTOConverter.toDTOList(allCommunities);
  }

  public List<CommunityDTO> getCommunitiesByName(String name) {

    List<Community> allByNameContainingIgnoreCase =
        communityRepository.findAllByNameContainingIgnoreCase(name);
    return CommunityToDTOConverter.toDTOList(allByNameContainingIgnoreCase);
  }

  public Community getCommunityByName(String communityName) {
    Community communityByName =
        communityRepository
            .findCommunityByName(communityName)
            .orElseThrow(() -> new CommunityNotFoundException(communityName));

    return communityByName;
  }

  public CommunityDTO updateCommunity(String communityName, Community updatedCommunity) {
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
