package com.setu.service;

import com.setu.dto.request.RegisterRequest;
import com.setu.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

}