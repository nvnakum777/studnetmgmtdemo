package com.studentmgmt.mapper;

import com.studentmgmt.dto.StudentCourseResponse;
import com.studentmgmt.entity.StudentCourse;
import org.springframework.stereotype.Component;

@Component
public class StudentCourseMapper {

    public StudentCourseResponse toResponse(StudentCourse entity) {
        if (entity == null) {
            return null;
        }

        StudentCourseResponse response = new StudentCourseResponse();
        response.setId(entity.getId());
        response.setCourseId(entity.getCourse().getId());
        response.setCourseName(entity.getCourse().getCourseName());
        response.setEnrolledDate(entity.getEnrolledDate());
        response.setStatus(entity.getStatus());
        return response;
    }
}