package com.studentmgmt.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Long id;

    private String username;

    private String role;

    private String token;

    private long expiresIn;

}