package com.Gogedit.persistence.repository;

import com.Gogedit.persistence.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findAllByOrderByCreatedDateDesc();
}
