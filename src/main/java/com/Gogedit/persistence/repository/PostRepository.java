package com.Gogedit.persistence.repository;

import com.Gogedit.dto.post.PostSummaryDTO;
import com.Gogedit.persistence.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, String> {

  @Query(
      """
        SELECT new com.Gogedit.dto.post.PostSummaryDTO(p.id, p.title, p.body, c.name, p.createdDate, SIZE(p.comments), author.username)
        FROM Post p
        LEFT JOIN p.community c
        LEFT JOIN p.author author
        ORDER BY p.createdDate DESC
        LIMIT 20
    """)
  List<PostSummaryDTO> findMostRecentPostsLimit20();

  @Query(
    """
        SELECT new com.Gogedit.dto.post.PostSummaryDTO(p.id, p.title, p.body, c.name, p.createdDate, SIZE(p.comments), author.username)
        FROM Post p
        LEFT JOIN p.community c
        LEFT JOIN p.author author
        WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
      """)
  List<PostSummaryDTO> findAllByTitleContainingIgnoreCase(String keyword);

  @Query(
      """
    SELECT new com.Gogedit.dto.post.PostSummaryDTO(p.id, p.title, p.body, c.name, p.createdDate, SIZE(p.comments), author.username)
    FROM Post p
    LEFT JOIN p.community c
    LEFT JOIN p.author author
    WHERE c.name = :communityName
    ORDER BY p.createdDate DESC
""")
  List<PostSummaryDTO> findAllPostsByCommunityName(String communityName);

  @Query(
          """
        SELECT new com.Gogedit.dto.post.PostSummaryDTO(p.id, p.title, p.body, c.name, p.createdDate, SIZE(p.comments), author.username)
        FROM Post p
        LEFT JOIN p.community c
        LEFT JOIN p.author author
        WHERE author.username = :username
        ORDER BY p.createdDate DESC
    """)
  List<PostSummaryDTO> findAllByUsername(String username);
}
