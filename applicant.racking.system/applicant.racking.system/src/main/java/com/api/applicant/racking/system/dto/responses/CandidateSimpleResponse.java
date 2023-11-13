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
    private String phone;
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
        this.phone = candidate.getPhone();
        this.position_professional = candidate.getPosition_professional();
        this.seniority = candidate.getSeniority();
        this.graduation = candidate.getGraduation();
        this.certification = candidate.getCertification();
        this.preference_of_atuation = candidate.getPreference_of_atuation();
        this.locality = candidate.getLocality();
        this.linkedin_profile = candidate.getLinkedin_profile();

        //anos e meses de experiencia do candidato
        int totalMonths = candidate.getMonths_length_of_experience();
        int totalYears = candidate.getYears_length_of_experience();

        if (totalMonths >= 12) {
            int yearsFromMonths = totalMonths / 12;
            totalMonths %= 12;
            totalYears += yearsFromMonths;
        }

        String lengh = totalYears + " ano(s) " + totalMonths + " mês(es)";
        this.length_of_experience = (lengh);
    }
}
