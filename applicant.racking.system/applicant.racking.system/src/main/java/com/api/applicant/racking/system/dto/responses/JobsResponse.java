package com.api.applicant.racking.system.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobsResponse {

    private String job_title;
    private String locality;
    private Boolean is_remote;
    @JsonIgnore
    private Double salary_proposal;
    @JsonIgnore
    private Boolean is_active;
    private String open_date;
    private String close_date;
    private String type_contract;
    private String seniority;
    @JsonIgnore
    private Boolean is_hybrid;
    @JsonIgnore
    private Boolean is_mandatory_training;
    private String duration_inDays;

    private String remote_converted;
    private String is_hibrid_converted;
    private String is_active_converted;
    private String is_mandatory_training_converted;
    private String salary_converted;
    private String enterprise;

}
