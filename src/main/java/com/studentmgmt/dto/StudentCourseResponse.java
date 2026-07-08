package com.studentmgmt.dto;

import com.studentmgmt.enums.EnrollmentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentCourseResponse {

    private Long id;

    private Long courseId;

    private String courseName;

    private LocalDate enrolledDate;

    private EnrollmentStatus status;

}
