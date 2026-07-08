package com.studentmgmt.security;

import com.studentmgmt.entity.Admin;
import com.studentmgmt.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Admin not found"));

        return new User(
                admin.getUsername(),
                admin.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(admin.getRole().name())
                )
        );
    }
}