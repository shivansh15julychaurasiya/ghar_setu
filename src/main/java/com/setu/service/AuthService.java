package com.setu.service;

import com.setu.dto.request.LoginRequest;
import com.setu.dto.request.RegisterRequest;
import com.setu.dto.response.LoginResponse;
import com.setu.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

   // LoginResponse login(VerifyOtpRequest request);
    LoginResponse login(LoginRequest request);

}