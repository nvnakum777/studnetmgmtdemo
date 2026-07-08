package com.studentmgmt.dto;
import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {

    private Long id;

    private String courseName;

    private String description;

    private String courseType;

    private Integer duration;

    private List<String> topics;

}
