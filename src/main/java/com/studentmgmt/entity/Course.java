package com.studentmgmt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String courseName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String courseType;

    @Column(nullable = false)
    private Integer duration;

    @ElementCollection
    @CollectionTable(
            name = "course_topics",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Column(name = "topic")
    private List<String> topics = new ArrayList<>();

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<StudentCourse> enrollments = new ArrayList<>();

}
