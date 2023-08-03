package com.Gogedit.service;

import com.Gogedit.converter.UserToDTOConverter;
import com.Gogedit.dto.UserDTO;
import com.Gogedit.dto.UserRegisterRequestDTO;
import com.Gogedit.persistence.entity.AppUser;
import com.Gogedit.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        //TODO: Add validation
        AppUser appUser = new AppUser();
    appUser.setUsername(userRegisterRequestDTO.getUsername());
    appUser.setPassword(userRegisterRequestDTO.getPassword());
        AppUser savedUser = saveUser(appUser);
        return UserToDTOConverter.toDTO(savedUser);
    }

    private AppUser saveUser(AppUser newAppUser) {
        return userRepository.save(newAppUser);
    }
}
