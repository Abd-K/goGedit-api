package com.Gogedit.persistence.repository;

import com.Gogedit.persistence.entity.AppUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<AppUser, String> {
  Optional<AppUser> findAppUserByUsername(String username);

  @Query(
      """
                SELECT u FROM AppUser u
                WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))
                GROUP BY u
              """)
  List<AppUser> findAllByUsernameContainingIgnoreCase(String keyword);
}
