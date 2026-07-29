package com.setu.service;

import com.setu.dto.response.UserResponse;
import com.setu.entity.User;

public interface UserService {

    User getByMobile(String mobile);

    User getById(Long id);

    UserResponse getCurrentUser();

    UserResponse toResponse(User user);
}