package com.studentmgmt.controller;

import com.studentmgmt.dto.*;
import com.studentmgmt.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponse<LoginResponse>> adminLogin(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.adminLogin(request);

        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Admin login successful")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/student/login")
    public ResponseEntity<ApiResponse<LoginResponse>> studentLogin(
            @Valid @RequestBody StudentLoginRequest request) {

        LoginResponse response = authService.studentLogin(request);

        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Student login successful")
                        .data(response)
                        .build()
        );
    }
}