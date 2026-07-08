package com.studentmgmt.service.impl;

import com.studentmgmt.dto.LoginRequest;
import com.studentmgmt.dto.StudentLoginRequest;
import com.studentmgmt.dto.LoginResponse;
import com.studentmgmt.entity.Admin;
import com.studentmgmt.entity.Student;
import com.studentmgmt.exception.UnauthorizedException;
import com.studentmgmt.repository.AdminRepository;
import com.studentmgmt.repository.StudentRepository;
import com.studentmgmt.security.JwtService;
import com.studentmgmt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponse adminLogin(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid username or password."));

        String token = jwtService.generateToken(
                admin.getId(),
                admin.getUsername(),
                admin.getRole().name()
        );

        return LoginResponse.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .role(admin.getRole().name())
                .token(token)
                .expiresIn(86400000L)
                .build();
    }

    @Override
    public LoginResponse studentLogin(StudentLoginRequest request) {

        Student student = studentRepository
                .findByStudentCodeAndDateOfBirth(
                        request.getStudentCode(),
                        request.getDateOfBirth()
                )
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid student credentials."));

        String token = jwtService.generateToken(
                student.getId(),
                student.getStudentCode(),
                "STUDENT"
        );

        return LoginResponse.builder()
                .id(student.getId())
                .username(student.getStudentCode())
                .role("STUDENT")
                .token(token)
                .expiresIn(86400000L)
                .build();
    }
}
