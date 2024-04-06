package com.prueba.backend_prueba.controller;

import com.prueba.backend_prueba.dto.UserDTO;
import com.prueba.backend_prueba.model.User;
import com.prueba.backend_prueba.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }



}
