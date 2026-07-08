package com.studentmgmt.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentLoginRequest {

    @NotBlank(message = "Student code is required")
    private String studentCode;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

}