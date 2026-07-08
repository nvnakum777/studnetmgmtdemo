package com.studentmgmt.service;

import com.studentmgmt.dto.LoginRequest;
import com.studentmgmt.dto.StudentLoginRequest;
import com.studentmgmt.dto.LoginResponse;

public interface AuthService {

    LoginResponse adminLogin(LoginRequest request);

    LoginResponse studentLogin(StudentLoginRequest request);

}