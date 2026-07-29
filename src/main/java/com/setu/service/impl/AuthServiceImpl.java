package com.setu.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.setu.dto.request.LoginRequest;
import com.setu.dto.request.RegisterRequest;
import com.setu.dto.response.LoginResponse;
import com.setu.dto.response.UserResponse;
import com.setu.entity.Role;
import com.setu.entity.User;
import com.setu.entity.UserRole;
import com.setu.exception.BadRequestException;
import com.setu.exception.DuplicateResourceException;
import com.setu.exception.ResourceNotFoundException;
import com.setu.mapper.AuthMapper;
import com.setu.repository.RoleRepository;
import com.setu.repository.UserRepository;
import com.setu.repository.UserRoleRepository;
import com.setu.service.AuthService;
import com.setu.service.JwtService;
import com.setu.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuthMapper authMapper;
    
    private final UserService userService;

    private final JwtService jwtService;

    
   
    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByMobile(request.getMobile())) {
            throw new DuplicateResourceException("Mobile number already registered.");
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && userRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException("Email already registered.");
        }

        User user = authMapper.toEntity(request);

        user.setAccountStatus("ACTIVE");
        user.setMobileVerified(false);
        user.setEmailVerified(false);

        user = userRepository.save(user);

        Role role = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Default role CUSTOMER not found."));

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleRepository.save(userRole);

        return authMapper.toResponse(user);
    }
    
    
    
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userService.getByMobile(request.getMobile());

        if (!Boolean.TRUE.equals(user.getMobileVerified())) {
            throw new BadRequestException("Please verify OTP first.");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .user(userService.toResponse(user))
                .build();
    }
    
    
    
    
}
