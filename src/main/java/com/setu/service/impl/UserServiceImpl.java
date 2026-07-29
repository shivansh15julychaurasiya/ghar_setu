package com.setu.service.impl;

import org.springframework.stereotype.Service;

import com.setu.dto.response.UserResponse;
import com.setu.entity.User;
import com.setu.exception.ResourceNotFoundException;
import com.setu.repository.UserRepository;
import com.setu.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getByMobile(String mobile) {

        return userRepository.findByMobile(mobile)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }

    @Override
    public User getById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }

    @Override
    public UserResponse getCurrentUser() {
        return null;
    }

    @Override
    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .accountStatus(user.getAccountStatus())
                .build();
    }
}