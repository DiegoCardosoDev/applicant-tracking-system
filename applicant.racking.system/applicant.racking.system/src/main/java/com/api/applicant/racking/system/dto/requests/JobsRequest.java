package com.api.applicant.racking.system.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class JobsRequest {

    private  String job_title;
    private  String locality;
    private Boolean is_remote;
    private Double salary_proposal;
    private Boolean is_active;
    private LocalDate open_date;
    private String type_contract;
    private String seniority;
    private Boolean is_hybrid;
    private Boolean is_mandatory_training;

    private Long enterprise_id;
}
