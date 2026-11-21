package com.securityexample.security.service;

import com.securityexample.security.dto.UserRequestDTO;
import com.securityexample.security.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    void deleteUser(Long id);
}
