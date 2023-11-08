package com.api.applicant.racking.system.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "jobs")
@Entity
public class JobsEntity {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private  String job_title;
    private  String locality;
    private Boolean is_remote;
    private Double salary_proposal;
    private Boolean is_active;
    private LocalDate open_date;
    private LocalDate close_date;
    private String type_contract;
    private String seniority;
    private Boolean is_hybrid;
    private Boolean is_mandatory_training;
    private Integer duration_days;

    @ManyToOne
    @JoinColumn (name = "enterprise_id")
    private EnterpriseEntity enterprise;

    @ManyToMany (mappedBy = "jobs")
    private List<CandidateEntity> candidates;
}
