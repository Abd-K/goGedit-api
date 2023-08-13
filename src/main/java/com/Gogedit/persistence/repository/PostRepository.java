package com.Gogedit.persistence.repository;

import com.Gogedit.dto.post.PostSummaryDTO;
import com.Gogedit.persistence.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, String> {

  @Query(
          """
        SELECT new com.Gogedit.dto.post.PostSummaryDTO(p.id, p.title, p.body, c.name, SIZE(p.comments), p.createdDate)
        FROM Post p
        JOIN p.community c
        ORDER BY p.createdDate DESC
        LIMIT 20
    """)
    List<PostSummaryDTO> findAllByOrderByCreatedDateDesc();

  @Query(
      """
    SELECT new com.Gogedit.dto.post.PostSummaryDTO(p.id, p.title, p.body, c.name, SIZE(p.comments), p.createdDate)
    FROM Post p
    JOIN p.community c
    WHERE c.name = :communityName
""")
  List<PostSummaryDTO> findAllPostsByCommunityName(String communityName);
}
