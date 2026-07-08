package com.studentmgmt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CourseRequest {

    @NotBlank
    private String courseName;

    private String description;

    @NotBlank
    private String courseType;

    @NotNull
    private Integer duration;

    private List<String> topics;

}