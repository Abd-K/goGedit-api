package com.Gogedit.persistence.repository;

import com.Gogedit.persistence.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {

}
