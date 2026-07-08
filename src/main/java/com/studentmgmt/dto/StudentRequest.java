package com.studentmgmt.dto;

import com.studentmgmt.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentRequest {

    @NotBlank
    private String studentCode;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String mobileNumber;

    private String fatherName;

    private String motherName;

    private List<AddressRequest> addresses;

}