package com.studentmgmt.mapper;

import com.studentmgmt.dto.UpdateStudentProfileRequest;
import com.studentmgmt.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentUpdateMapper {

    public void updateStudent(UpdateStudentProfileRequest request, Student student) {
        if (request == null || student == null) {
            return;
        }

        if (request.getEmail() != null) {
            student.setEmail(request.getEmail());
        }
        if (request.getMobileNumber() != null) {
            student.setMobileNumber(request.getMobileNumber());
        }
        if (request.getFatherName() != null) {
            student.setFatherName(request.getFatherName());
        }
        if (request.getMotherName() != null) {
            student.setMotherName(request.getMotherName());
        }
    }
}