package com.setu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setu.common.ApiResponse;
import com.setu.dto.response.UserResponse;
import com.setu.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me() {

        return ResponseEntity.ok(

                ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message("Current user fetched successfully.")
                        .data(userService.getCurrentUser())
                        .build()
        );
    }

}