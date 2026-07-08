package com.studentmgmt.controller;

import com.studentmgmt.dto.*;
import com.studentmgmt.security.UserPrincipal;
import com.studentmgmt.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(
            @Valid @RequestBody CourseRequest request) {

        CourseResponse response = courseService.createCourse(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CourseResponse>builder()
                        .success(true)
                        .message("Course created successfully")
                        .data(response)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest request) {

        CourseResponse response = courseService.updateCourse(id, request);

        return ResponseEntity.ok(
                ApiResponse.<CourseResponse>builder()
                        .success(true)
                        .message("Course updated successfully")
                        .data(response)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getAllCourses(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size) {

        Page<CourseResponse> response =
                courseService.getAllCourses(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<CourseResponse>>builder()
                        .success(true)
                        .message("Courses fetched successfully")
                        .data(response)
                        .build());
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<ApiResponse<Void>> assignCourse(

            @PathVariable Long courseId,

            @PathVariable Long studentId) {

        courseService.assignCourse(studentId, courseId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Course assigned successfully")
                        .build());
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<ApiResponse<Void>> leaveCourse(

            @PathVariable Long courseId,

            @PathVariable Long studentId) {

        courseService.leaveCourse(studentId, courseId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Course removed successfully")
                        .build());
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getCourseStudents(
            @PathVariable Long courseId) {

        List<StudentResponse> response =
                courseService.getCourseStudents(courseId);

        return ResponseEntity.ok(
                ApiResponse.<List<StudentResponse>>builder()
                        .success(true)
                        .message("Students fetched successfully")
                        .data(response)
                        .build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getStudentCourses(
            @PathVariable Long studentId) {

        List<CourseResponse> response =
                courseService.getStudentCourses(studentId);

        return ResponseEntity.ok(
                ApiResponse.<List<CourseResponse>>builder()
                        .success(true)
                        .message("Courses fetched successfully")
                        .data(response)
                        .build());
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getOwnCourses(
            Authentication authentication) {

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        List<CourseResponse> response =
                courseService.getStudentCourses(principal.getId());

        return ResponseEntity.ok(
                ApiResponse.<List<CourseResponse>>builder()
                        .success(true)
                        .message("Courses fetched successfully")
                        .data(response)
                        .build());
    }

    @DeleteMapping("/me/{courseId}")
    public ResponseEntity<ApiResponse<Void>> leaveOwnCourse(
            @PathVariable Long courseId,
            Authentication authentication) {

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        courseService.leaveCourse(principal.getId(), courseId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Course removed successfully")
                        .build());
    }
}