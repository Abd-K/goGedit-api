package com.Gogedit.controller;

import com.Gogedit.persistence.entity.Community;
import com.Gogedit.service.CommunityService;
import jakarta.validation.Valid;
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

import java.util.List;

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
  public Community createCommunity(@RequestBody Community communityRequest) {
    return communityService.createCommunity(communityRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Community> getCommunities() {
    return communityService.getCommunities();
  }

  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<Community> getCommunitiesByKeyword(@RequestParam String name) {
    return communityService.getCommunitiesByName(name);
  }

  @GetMapping("/{communityId}")
  @ResponseStatus(HttpStatus.OK)
  public Community getCommunity(@PathVariable String communityId) {
    return communityService.getCommunityById(communityId);
  }

  @PutMapping("/{communityId}")
  @ResponseStatus(HttpStatus.OK)
  public Community updateCommunity(@Valid @RequestBody Community updatedCommunity, @PathVariable String communityId) {
    return communityService.updateCommunity(communityId, updatedCommunity);
  }

}
