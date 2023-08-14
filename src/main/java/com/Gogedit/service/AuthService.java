package com.Gogedit.service;

import com.Gogedit.converter.UserToDTOConverter;
import com.Gogedit.dto.UserDTO;
import com.Gogedit.dto.UserRegisterRequestDTO;
import com.Gogedit.dto.UserSignInRequestDTO;
import com.Gogedit.persistence.entity.AppUser;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
        private final UserService userService;

        public AuthService(UserService userService) {
        this.userService = userService;
        }

    public UserDTO register(UserRegisterRequestDTO userRegisterRequestDTO) {
        Optional<AppUser> userOptional =
                userService.getUserOptionalByUsername(userRegisterRequestDTO.getUsername());

        if (userOptional.isPresent()) throw new IllegalArgumentException("Username already exists");
        AppUser savedUser = userService.createUser(userRegisterRequestDTO);
        return UserToDTOConverter.toDTO(savedUser);
    }

    public UserDTO signIn(UserSignInRequestDTO userSignInRequestDTO) {
        String userSignInUsername = userSignInRequestDTO.getUsername();
        String userSignInPassword = userSignInRequestDTO.getPassword();
        
        AppUser user = userService.getUserByUsername(userSignInUsername);
        if (userSignInPassword.equals(user.getPassword())) {
            return UserToDTOConverter.toDTO(user);
        }
        throw new IllegalArgumentException("Password is incorrect");
    }

}
