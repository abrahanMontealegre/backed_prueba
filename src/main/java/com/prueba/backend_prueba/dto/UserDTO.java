package com.prueba.backend_prueba.dto;

import com.prueba.backend_prueba.model.ERole;
import com.prueba.backend_prueba.model.Role;
import com.prueba.backend_prueba.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private Set<String> roles;


    public static User toEntity(UserDTO userDTO) {
        Set<Role> roles = userDTO.getRoles().stream()
                .map(roleName -> new Role(ERole.valueOf(roleName)))
                .collect(Collectors.toSet());
        return new User(
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                roles
        );
    }

    public static UserDTO fromEntity(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
        return new UserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                roleNames
        );
    }
}
