package com.Gogedit.service;

import com.Gogedit.dto.CreateUserDTO;
import com.Gogedit.persistence.entity.AppUser;
import com.Gogedit.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createUser(CreateUserDTO createUserDTO) {
        //TODO: Add validation
        AppUser appUser = new AppUser();
    appUser.setUsername(createUserDTO.getUsername());
    appUser.setPassword(createUserDTO.getPassword());
    return saveUser(appUser);
    }

    private AppUser saveUser(AppUser newAppUser) {
        return userRepository.save(newAppUser);
    }
}
