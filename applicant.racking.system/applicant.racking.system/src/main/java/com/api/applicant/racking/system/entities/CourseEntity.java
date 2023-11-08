package com.api.applicant.racking.system.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "courses")
@Entity
public class CourseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;
    private String course_name;
    private String course_instituition;
    private Integer duration;
}
