package com.studentmgmt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class UpdateStudentProfileRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String mobileNumber;

    private String fatherName;

    private String motherName;

    private List<AddressRequest> addresses;

}