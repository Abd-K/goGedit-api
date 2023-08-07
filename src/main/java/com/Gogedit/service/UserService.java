package com.Gogedit.service;

import com.Gogedit.converter.UserToDTOConverter;
import com.Gogedit.dto.UserDTO;
import com.Gogedit.dto.UserRegisterRequestDTO;
import com.Gogedit.dto.UserSignInRequestDTO;
import com.Gogedit.persistence.entity.AppUser;
import com.Gogedit.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDTO createUser(UserRegisterRequestDTO userRegisterRequestDTO) {
    Optional<AppUser> userOptional =
        userRepository.findAppUserByUsername(userRegisterRequestDTO.getUsername());

    if (userOptional.isPresent())
      throw new IllegalArgumentException("Username already exists");
    if (userRegisterRequestDTO.getUsername().contains(" "))
      throw new IllegalArgumentException("Username cannot contain spaces");
    if (userRegisterRequestDTO.getPassword().contains(" "))
      throw new IllegalArgumentException("Password cannot contain spaces");

    AppUser appUser = new AppUser();
    appUser.setUsername(userRegisterRequestDTO.getUsername());
    appUser.setPassword(userRegisterRequestDTO.getPassword());
    AppUser savedUser = saveUser(appUser);

    return UserToDTOConverter.toDTO(savedUser);
  }

  public UserDTO getUser(UserSignInRequestDTO userSignInRequestDTO) {
    String userSignInUsername = userSignInRequestDTO.getUsername();
    String userSignInPassword = userSignInRequestDTO.getPassword();

    Optional<AppUser> userOptional =
            userRepository.findAppUserByUsername(userSignInUsername);
      if (userOptional.isPresent()) {
        AppUser user = userOptional.get();

        if (userSignInPassword.equals(user.getPassword())) {
                return UserToDTOConverter.toDTO(user);
        }
      else {
        throw new IllegalArgumentException("Password is incorrect");
      }
    }
    else {
      throw new IllegalArgumentException("Username is incorrect");
    }
  }

  private AppUser saveUser(AppUser newAppUser) {
    return userRepository.save(newAppUser);
  }

}
