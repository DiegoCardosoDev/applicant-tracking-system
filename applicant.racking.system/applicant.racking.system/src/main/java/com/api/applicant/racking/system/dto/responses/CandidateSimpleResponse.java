package com.api.applicant.racking.system.dto.responses;


import com.api.applicant.racking.system.entities.CandidateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CandidateSimpleResponse {


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


    public CandidateSimpleResponse(CandidateEntity candidate){
        this.name = candidate.getName();
        this.age = candidate.getAge();
        this.email = candidate.getEmail();
        this.position_professional = candidate.getPosition_professional();
        this.seniority = candidate.getSeniority();
        this.graduation = candidate.getGraduation();
        this.certification = candidate.getCertification();
        this.preference_of_atuation = candidate.getPreference_of_atuation();
        this.locality = candidate.getLocality();
        this.linkedin_profile = candidate.getLinkedin_profile();
    }
}
