package com.studentmgmt.repository;

import com.studentmgmt.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentCode(String studentCode);

    Optional<Student> findByStudentCodeAndDateOfBirth(
            String studentCode,
            LocalDate dateOfBirth
    );

    Page<Student> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
    Optional<Student> findByEmail(String email);

    boolean existsByStudentCode(String studentCode);

    boolean existsByEmail(String email);

}