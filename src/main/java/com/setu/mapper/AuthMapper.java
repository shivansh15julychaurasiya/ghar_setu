package com.setu.mapper;

//package com.gharsetu.mapper;

import org.springframework.stereotype.Component;

import com.setu.dto.request.RegisterRequest;
import com.setu.dto.response.UserResponse;
import com.setu.entity.User;

@Component
public class AuthMapper {

    public User toEntity(RegisterRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());

        return user;
    }

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