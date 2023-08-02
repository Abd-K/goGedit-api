package com.Gogedit.persistence.repository;

import com.Gogedit.persistence.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, String> {

  //TODO costly?
  List<Community> findAllByNameContainingIgnoreCase(String keyword);
  Community findCommunityByName(String name);
}
