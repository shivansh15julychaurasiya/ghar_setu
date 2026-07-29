package com.setu.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setu.common.ApiResponse;
import com.setu.dto.request.LoginRequest;
import com.setu.dto.request.RegisterRequest;
import com.setu.dto.request.SendOtpRequest;
import com.setu.dto.request.VerifyOtpRequest;
import com.setu.dto.response.LoginResponse;
import com.setu.dto.response.SendOtpResponse;
import com.setu.dto.response.UserResponse;
import com.setu.dto.response.VerifyOtpResponse;
import com.setu.service.AuthService;
import com.setu.service.OtpService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    
    private final OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = authService.register(request);

        ApiResponse<UserResponse> apiResponse =
                ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message("User registered successfully.")
                        .data(response)
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }
    
    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<SendOtpResponse>> sendOtp(
            @Valid @RequestBody SendOtpRequest request) {

        SendOtpResponse response = otpService.sendOtp(request);

        ApiResponse<SendOtpResponse> apiResponse =
                ApiResponse.<SendOtpResponse>builder()
                        .success(true)
                        .message("OTP sent successfully.")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<VerifyOtpResponse>> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request) {

        VerifyOtpResponse response = otpService.verifyOtp(request);

        ApiResponse<VerifyOtpResponse> apiResponse =
                ApiResponse.<VerifyOtpResponse>builder()
                        .success(true)
                        .message("OTP verified successfully.")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
    
    
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login successful.")
                        .data(response)
                        .build());
    }
    
    
}