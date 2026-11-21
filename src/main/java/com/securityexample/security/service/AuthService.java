package com.securityexample.security.service;

import com.securityexample.security.dto.CreateUserDTO;
import com.securityexample.security.dto.RegisterUserDto;
import com.securityexample.security.entity.Role;
import com.securityexample.security.entity.User;
import com.securityexample.security.exception.DuplicateResourceException;
import com.securityexample.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public RegisterUserDto registerUserDto(CreateUserDTO createUserDTO) {
        String normalizedEmail = createUserDTO.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new DuplicateResourceException("User with email '" + createUserDTO.getEmail() + "' already exists");
        }

        User user = new User();
        user.setName(createUserDTO.getName().trim());
        user.setEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        return new RegisterUserDto(savedUser.getName(), savedUser.getId());
    }
}
