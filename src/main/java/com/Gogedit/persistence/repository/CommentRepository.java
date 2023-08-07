package com.Gogedit.persistence.repository;

import com.Gogedit.persistence.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {}
