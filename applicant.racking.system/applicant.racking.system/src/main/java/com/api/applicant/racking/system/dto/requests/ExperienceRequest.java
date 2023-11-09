package com.api.applicant.racking.system.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ExperienceRequest {

    private String enterprise_name;
    private String locality;

    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate entry_date;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate exit_date;
}
