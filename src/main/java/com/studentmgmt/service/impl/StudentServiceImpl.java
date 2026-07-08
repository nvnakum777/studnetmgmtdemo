package com.studentmgmt.service.impl;

import com.studentmgmt.dto.AddressRequest;
import com.studentmgmt.dto.StudentRequest;
import com.studentmgmt.dto.UpdateStudentProfileRequest;
import com.studentmgmt.dto.StudentResponse;
import com.studentmgmt.entity.Address;
import com.studentmgmt.entity.Student;
import com.studentmgmt.exception.DuplicateResourceException;
import com.studentmgmt.exception.ResourceNotFoundException;
import com.studentmgmt.mapper.StudentMapper;
import com.studentmgmt.repository.StudentRepository;
import com.studentmgmt.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        if (studentRepository.existsByStudentCode(request.getStudentCode())) {
            throw new DuplicateResourceException("Student code already exists.");
        }

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
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

        List<Address> addresses = new ArrayList<>();

        if (request.getAddresses() != null) {

            for (AddressRequest dto : request.getAddresses()) {

                Address address = Address.builder()
                        .addressType(dto.getAddressType())
                        .addressLine(dto.getAddressLine())
                        .city(dto.getCity())
                        .state(dto.getState())
                        .country(dto.getCountry())
                        .postalCode(dto.getPostalCode())
                        .student(student)
                        .build();

                addresses.add(address);
            }

        }

        student.setAddresses(addresses);

        Student saved = studentRepository.save(student);

        return studentMapper.toResponse(saved);
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        return studentMapper.toResponse(student);
    }


    @Override
    public Page<StudentResponse> getAllStudents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return studentRepository.findAll(pageable)
                .map(studentMapper::toResponse);
    }
    @Override
    public Page<StudentResponse> searchStudents(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return studentRepository
                .findByNameContainingIgnoreCase(name, pageable)
                .map(studentMapper::toResponse);
    }


    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        student.setName(request.getName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setEmail(request.getEmail());
        student.setMobileNumber(request.getMobileNumber());
        student.setFatherName(request.getFatherName());
        student.setMotherName(request.getMotherName());

        student.getAddresses().clear();

        if (request.getAddresses() != null) {

            for (AddressRequest dto : request.getAddresses()) {

                Address address = Address.builder()
                        .addressType(dto.getAddressType())
                        .addressLine(dto.getAddressLine())
                        .city(dto.getCity())
                        .state(dto.getState())
                        .country(dto.getCountry())
                        .postalCode(dto.getPostalCode())
                        .student(student)
                        .build();

                student.getAddresses().add(address);
            }
        }

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse updateProfile(Long studentId,
                                         UpdateStudentProfileRequest request) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        student.setEmail(request.getEmail());
        student.setMobileNumber(request.getMobileNumber());
        student.setFatherName(request.getFatherName());
        student.setMotherName(request.getMotherName());

        student.getAddresses().clear();

        if (request.getAddresses() != null) {

            for (AddressRequest dto : request.getAddresses()) {

                Address address = Address.builder()
                        .addressType(dto.getAddressType())
                        .addressLine(dto.getAddressLine())
                        .city(dto.getCity())
                        .state(dto.getState())
                        .country(dto.getCountry())
                        .postalCode(dto.getPostalCode())
                        .student(student)
                        .build();

                student.getAddresses().add(address);
            }
        }

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        studentRepository.delete(student);
    }



}