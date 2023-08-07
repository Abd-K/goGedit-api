package com.Gogedit.controller;

import com.Gogedit.dto.UserDTO;
import com.Gogedit.dto.UserRegisterRequestDTO;
import com.Gogedit.dto.UserSignInRequestDTO;
import com.Gogedit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.createUser(userRegisterRequestDTO);
    }

    @GetMapping("sign-in")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO signIn(@RequestBody UserSignInRequestDTO userSignInRequestDTO) {
        return userService.getUser(userSignInRequestDTO);
    }
}
