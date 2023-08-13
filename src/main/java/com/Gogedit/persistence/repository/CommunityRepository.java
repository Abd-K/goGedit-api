package com.Gogedit.persistence.repository;

import com.Gogedit.dto.community.CommunitySummaryDTO;
import com.Gogedit.persistence.entity.Community;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, String> {

  // TODO costly?
  @Query(
      """
                SELECT new com.Gogedit.dto.community.CommunitySummaryDTO(c.name, c.description, SIZE(p), c.createdDate)
                FROM Community c
                JOIN c.posts p
                WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
                GROUP BY c
        """)
  List<CommunitySummaryDTO> findAllByNameContainingIgnoreCase(String keyword);
  //  @Query(
  //      """
  //              SELECT c.name AS name, c.description AS description, SIZE(p) as postCount
  //              FROM Community c
  //              JOIN c.posts p
  //              WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
  //              GROUP BY c.id, c.name, c.description
  //          """)
  //  List<CommunitySummaryAbstraction> findAllByNameContainingIgnoreCase(String keyword);

  boolean existsByName(String name);

  @Query(
      """
      SELECT new com.Gogedit.dto.community.CommunitySummaryDTO(c.name, c.description, COUNT(p), c.createdDate)
      FROM Community c
      JOIN c.posts p
      WHERE c.name = :name
      GROUP BY c
   """)
  Optional<CommunitySummaryDTO> findCommunitySummaryByName(String name);

  Optional<Community> findCommunityByName(String name);

  //  @Query("SELECT c.name, c.description, COUNT(p) as postCount FROM Community c LEFT JOIN c.posts
  // p GROUP BY c.name, c.description")
  //  List<CommunitySummaryAbstraction> findAllWithPostCount();

  @Query(
      """
          SELECT new com.Gogedit.dto.community.CommunitySummaryDTO(c.name, c.description, COUNT(p), c.createdDate)
          FROM Community c
          JOIN c.posts p
          GROUP BY c
  """)
  List<CommunitySummaryDTO> findAllCommunitiesWithPostCounts();
}
