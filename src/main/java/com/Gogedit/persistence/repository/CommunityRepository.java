package com.Gogedit.persistence.repository;

import com.Gogedit.persistence.entity.Community;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, String> {

  //TODO costly?
  List<Community> findAllByNameContainingIgnoreCase(String keyword);
  boolean existsByName(String name);
  Community findCommunityByName(String name);
}
