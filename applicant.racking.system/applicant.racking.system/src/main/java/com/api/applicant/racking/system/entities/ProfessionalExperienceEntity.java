package com.api.applicant.racking.system.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "professional_experience")
@Entity
public class ProfessionalExperienceEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;
    private String enterprise_name;
    private String locality;
    private LocalDate entry_date;
    private LocalDate exit_date;
    private Integer duration;
}
