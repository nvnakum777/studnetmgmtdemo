package com.studentmgmt.service;

import com.studentmgmt.dto.CourseRequest;
import com.studentmgmt.dto.CourseResponse;
import com.studentmgmt.dto.StudentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CourseRequest request);

    CourseResponse updateCourse(Long id, CourseRequest request);

    void deleteCourse(Long id);


    Page<CourseResponse> getAllCourses(int page, int size);

    void assignCourse(Long studentId, Long courseId);

    void leaveCourse(Long studentId, Long courseId);

    List<CourseResponse> getStudentCourses(Long studentId);

    List<StudentResponse> getCourseStudents(Long courseId);

}