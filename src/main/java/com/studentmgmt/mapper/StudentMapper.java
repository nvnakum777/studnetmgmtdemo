package com.studentmgmt.mapper;

import com.studentmgmt.dto.StudentRequest;
import com.studentmgmt.dto.StudentResponse;
import com.studentmgmt.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final AddressMapper addressMapper;
    private final StudentCourseMapper studentCourseMapper;

    public Student toEntity(StudentRequest request) {
        if (request == null) {
            return null;
        }

        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setName(request.getName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setEmail(request.getEmail());
        student.setMobileNumber(request.getMobileNumber());
        student.setFatherName(request.getFatherName());
        student.setMotherName(request.getMotherName());
        return student;
    }

    public StudentResponse toResponse(Student entity) {
        if (entity == null) {
            return null;
        }

        StudentResponse response = new StudentResponse();
        response.setId(entity.getId());
        response.setStudentCode(entity.getStudentCode());
        response.setName(entity.getName());
        response.setDateOfBirth(entity.getDateOfBirth());
        response.setGender(entity.getGender());
        response.setEmail(entity.getEmail());
        response.setMobileNumber(entity.getMobileNumber());
        response.setFatherName(entity.getFatherName());
        response.setMotherName(entity.getMotherName());

        if (entity.getAddresses() != null) {
            response.setAddresses(
                    entity.getAddresses().stream()
                            .map(addressMapper::toResponse)
                            .collect(Collectors.toList())
            );
        }

        if (entity.getEnrollments() != null) {
            response.setCourses(
                    entity.getEnrollments().stream()
                            .map(studentCourseMapper::toResponse)
                            .collect(Collectors.toList())
            );
        }

        return response;
    }
}