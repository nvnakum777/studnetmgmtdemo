package com.studentmgmt.service;

import com.studentmgmt.dto.StudentRequest;
import com.studentmgmt.dto.UpdateStudentProfileRequest;
import com.studentmgmt.dto.StudentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);

    StudentResponse getStudentById(Long id);

    Page<StudentResponse> getAllStudents(int page , int size);

    Page<StudentResponse> searchStudents(String name, int page , int size);

    StudentResponse updateProfile(
            Long studentId,
            UpdateStudentProfileRequest request
    );

}