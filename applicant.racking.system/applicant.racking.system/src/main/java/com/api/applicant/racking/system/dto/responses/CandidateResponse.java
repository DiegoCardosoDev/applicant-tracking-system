package com.api.applicant.racking.system.dto.responses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CandidateResponse {


    private String name;
    private Integer age;
    private String email;
    private String position_professional;
    private String seniority;
    private String graduation;
    private String certification;
    private String preference_of_atuation;
    private String locality;
    private  String length_of_experience;
    private String linkedin_profile;
    private List<TechnologyStackResponse> stacks;
    private List<JobsResponse> applied;
    private List<ExperienceResponse> experiences;
    private List<CourseResponse> courses;
}
