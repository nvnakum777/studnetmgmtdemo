package com.studentmgmt.repository;

import com.studentmgmt.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByStudentId(Long studentId);

}