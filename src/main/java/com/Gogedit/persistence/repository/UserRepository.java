package com.Gogedit.persistence.repository;

import com.Gogedit.persistence.entity.AppUser;
import com.Gogedit.persistence.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findAppUserByUsername(String username);
}

