package com.studentmgmt.repository;

import com.studentmgmt.entity.Course;
import com.studentmgmt.entity.Student;
import com.studentmgmt.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    List<StudentCourse> findByStudent(Student student);

    List<StudentCourse> findByCourse(Course course);

    Optional<StudentCourse> findByStudentAndCourse(
            Student student,
            Course course
    );

    boolean existsByStudentAndCourse(
            Student student,
            Course course
    );

    void deleteByStudentAndCourse(
            Student student,
            Course course
    );

}