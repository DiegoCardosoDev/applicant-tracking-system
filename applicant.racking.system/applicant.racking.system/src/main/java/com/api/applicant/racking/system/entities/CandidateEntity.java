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
    private  Integer years_length_of_experience;
    private  Integer months_length_of_experience;
}
