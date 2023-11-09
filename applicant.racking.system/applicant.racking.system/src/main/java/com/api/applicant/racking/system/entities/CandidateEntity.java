package com.api.applicant.racking.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "candidate")
@Entity
public class CandidateEntity {


    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String seniority;
    private String graduation;
    private String preference_of_atuation;
    private String locality;
    private String certification;
    private String linkedin_profile;
    private String position_professional;
    private  Integer years_length_of_experience;
    private  Integer months_length_of_experience;

    @ManyToMany
    @JoinTable(
            name = "candidate_stack",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "stack_id")
    )
    private List<TechnologyStackEntity> stack = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "candidate_jobs", // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<JobsEntity> jobs = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "candidate_experience",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "professional_experience_id")
    )
    private List<ProfessionalExperienceEntity> professionalExperienceEntities = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "candidate_courses",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id")
    )
    private List<CourseEntity> coursesEntities = new ArrayList<>();
}
