package com.studentmgmt.dto;
import com.studentmgmt.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentResponse {

    private Long id;

    private String studentCode;

    private String name;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String email;

    private String mobileNumber;

    private String fatherName;

    private String motherName;

    private List<AddressResponse> addresses;

    private List<StudentCourseResponse> courses;

}