package com.studentmgmt.config;

import com.studentmgmt.entity.Admin;
import com.studentmgmt.enums.Role;
import com.studentmgmt.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (adminRepository.count() == 0) {

            Admin admin = Admin.builder()

                    .username("admin")

                    .password(passwordEncoder.encode("admin123"))

                    .role(Role.ADMIN)

                    .build();

            adminRepository.save(admin);

            System.out.println("Default admin created.");

        }

    }

}