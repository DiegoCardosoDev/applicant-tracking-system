package com.api.applicant.racking.system.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "jobs")
@Entity
public class JobsEntity {

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
}
