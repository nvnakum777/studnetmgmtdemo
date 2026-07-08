package com.studentmgmt.service.impl;

import com.studentmgmt.dto.CourseRequest;
import com.studentmgmt.dto.CourseResponse;
import com.studentmgmt.dto.StudentResponse;
import com.studentmgmt.entity.Course;
import com.studentmgmt.entity.Student;
import com.studentmgmt.entity.StudentCourse;
import com.studentmgmt.enums.EnrollmentStatus;
import com.studentmgmt.exception.BadRequestException;
import com.studentmgmt.exception.DuplicateResourceException;
import com.studentmgmt.exception.ResourceNotFoundException;
import com.studentmgmt.mapper.CourseMapper;
import com.studentmgmt.mapper.StudentMapper;
import com.studentmgmt.repository.CourseRepository;
import com.studentmgmt.repository.StudentCourseRepository;
import com.studentmgmt.repository.StudentRepository;
import com.studentmgmt.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;

    @Override
    public CourseResponse createCourse(CourseRequest request) {

        if (courseRepository.existsByCourseName(request.getCourseName())) {
            throw new DuplicateResourceException("Course already exists.");
        }

        Course course = courseMapper.toEntity(request);

        return courseMapper.toResponse(
                courseRepository.save(course)
        );
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest request) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found."));

        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setCourseType(request.getCourseType());
        course.setDuration(request.getDuration());
        course.setTopics(request.getTopics());

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found."));

        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    public Page<CourseResponse> getAllCourses(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return courseRepository.findAll(pageable)
                .map(courseMapper::toResponse);
    }

    @Override
    @Transactional
    public void assignCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found."));

        if (studentCourseRepository.existsByStudentAndCourse(student, course)) {
            throw new BadRequestException("Student already enrolled.");
        }

        StudentCourse enrollment = StudentCourse.builder()
                .student(student)
                .course(course)
                .status(EnrollmentStatus.ACTIVE)
                .build();

        studentCourseRepository.save(enrollment);
    }

    @Override
    @Transactional
    public void leaveCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found."));

        StudentCourse enrollment = studentCourseRepository
                .findByStudentAndCourse(student, course)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enrollment not found."));

        studentCourseRepository.delete(enrollment);
    }

    @Override
    public List<CourseResponse> getStudentCourses(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        return studentCourseRepository.findByStudent(student)
                .stream()
                .map(StudentCourse::getCourse)
                .map(courseMapper::toResponse)
                .toList();
    }

    @Override
    public List<StudentResponse> getCourseStudents(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found."));

        return studentCourseRepository.findByCourse(course)
                .stream()
                .map(StudentCourse::getStudent)
                .map(studentMapper::toResponse)
                .toList();
    }

}