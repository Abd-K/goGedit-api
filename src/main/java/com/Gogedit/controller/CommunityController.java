package com.Gogedit.controller;

import com.Gogedit.dto.community.CommunitySummaryDTO;
import com.Gogedit.dto.community.CreateCommunityDTO;
import com.Gogedit.persistence.entity.Community;
import com.Gogedit.service.CommunityService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/communities")
@Slf4j
public class CommunityController {

  private final CommunityService communityService;

  public CommunityController(CommunityService communityService) {
    this.communityService = communityService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommunityDTO createCommunity(@RequestBody CreateCommunityDTO createCommunityDTO) {
    return communityService.createCommunity(createCommunityDTO);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CommunitySummaryDTO> getCommunities() {
    return communityService.getCommunities();
  }

  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<CommunitySummaryDTO> searchCommunitiesByKeyword(@RequestParam String name) {
    return communityService.searchCommunitiesByKeyword(name);
  }

  @GetMapping("/{communityName}")
  @ResponseStatus(HttpStatus.OK)
  public CommunitySummaryDTO getCommunity(@PathVariable String communityName) {
    return communityService.getCommunitySummaryByName(communityName);
  }

  @PutMapping("/{communityId}")
  @ResponseStatus(HttpStatus.OK)
  public CommunityDTO updateCommunity(@Valid @RequestBody Community updatedCommunity, @PathVariable String communityId) {
    return communityService.updateCommunity(communityId, updatedCommunity);
  }
}
