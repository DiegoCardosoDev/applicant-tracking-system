package com.api.applicant.racking.system.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExperienceResponse {



    private Long id;
    private String enterprise_name;
    private String locality;
    private String entry_date;
    private String exit_date;
    private String durationInDays;
}
