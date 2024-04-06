package com.prueba.backend_prueba.service;


import com.prueba.backend_prueba.Auth.AuthResponse;
import com.prueba.backend_prueba.Auth.LoginRequest;
import com.prueba.backend_prueba.Auth.RegisterRequest;
import com.prueba.backend_prueba.Jwt.JwtService;
import com.prueba.backend_prueba.model.ERole;
import com.prueba.backend_prueba.model.Role;
import com.prueba.backend_prueba.model.User;
import com.prueba.backend_prueba.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {
        // Create a new Role instance using the ERole enum
        Role userRole = new Role(ERole.EXTERNO);

        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .firstname(request.getFirstname())
            .roles(new HashSet<>(List.of(userRole)))
            .lastname(request.getLastname())
            .build();


        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
