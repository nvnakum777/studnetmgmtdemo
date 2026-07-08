package com.studentmgmt.mapper;

import com.studentmgmt.dto.CourseRequest;
import com.studentmgmt.dto.CourseResponse;
import com.studentmgmt.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request) {
        if (request == null) {
            return null;
        }

        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setCourseType(request.getCourseType());
        course.setDuration(request.getDuration());
        course.setTopics(request.getTopics());
        return course;
    }

    public CourseResponse toResponse(Course course) {
        if (course == null) {
            return null;
        }

        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setDescription(course.getDescription());
        response.setCourseType(course.getCourseType());
        response.setDuration(course.getDuration());
        response.setTopics(course.getTopics());
        return response;
    }
}