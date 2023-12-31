package com.Gogedit.service;

import com.Gogedit.dto.UserRegisterRequestDTO;
import com.Gogedit.exceptions.UserNotFoundException;
import com.Gogedit.persistence.entity.AppUser;
import com.Gogedit.persistence.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public AppUser createUser(UserRegisterRequestDTO userRegisterRequestDTO) {
    if (userRegisterRequestDTO.getUsername().contains(" "))
      throw new IllegalArgumentException("Username cannot contain spaces");
    if (userRegisterRequestDTO.getPassword().contains(" "))
      throw new IllegalArgumentException("Password cannot contain spaces");

    AppUser appUser = new AppUser(userRegisterRequestDTO.getUsername(), userRegisterRequestDTO.getPassword());
    return saveUser(appUser);
  }

  public AppUser getUserByUsername(String username) {
    return userRepository
        .findAppUserByUsername(username)
        .orElseThrow(() -> new UserNotFoundException(username));
  }

  public Optional<AppUser> getUserOptionalByUsername(String username) {
    return userRepository.findAppUserByUsername(username);
  }

  private AppUser saveUser(AppUser newAppUser) {
    return userRepository.save(newAppUser);
  }
}
