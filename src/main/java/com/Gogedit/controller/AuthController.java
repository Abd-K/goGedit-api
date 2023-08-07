package com.Gogedit.controller;

import com.Gogedit.dto.UserDTO;
import com.Gogedit.dto.UserRegisterRequestDTO;
import com.Gogedit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/register")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.createUser(userRegisterRequestDTO);
    }
}
