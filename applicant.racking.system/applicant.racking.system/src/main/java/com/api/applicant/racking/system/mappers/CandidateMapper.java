package com.api.applicant.racking.system.mappers;


import com.api.applicant.racking.system.dto.requests.CandidateRequest;
import com.api.applicant.racking.system.dto.responses.CandidateResponse;
import com.api.applicant.racking.system.entities.CandidateEntity;
import com.api.applicant.racking.system.entities.CourseEntity;
import com.api.applicant.racking.system.entities.JobsEntity;
import com.api.applicant.racking.system.entities.TechnologyStackEntity;
import com.api.applicant.racking.system.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CandidateMapper {

    private final CandidateRepository candidateRepository;
    private final TechnologyStackRepository technologyStackRepository;
    private final JobsRepository jobsRepository;
    private final ExperienceRepository experienceRepository;
    private final CourseRepository courseRepository;

    public CandidateResponse mapToResponse(CandidateEntity candidateEntity){
        CandidateResponse candidateResponse = new CandidateResponse();
        candidateResponse.setName(candidateEntity.getName());
        candidateResponse.setEmail(candidateEntity.getEmail());
        candidateResponse.setAge(candidateEntity.getAge());
        candidateResponse.setPosition_professional(candidateEntity.getPosition_professional());
        candidateResponse.setSeniority(candidateEntity.getSeniority());
        candidateResponse.setLocality(candidateEntity.getLocality());
        candidateResponse.setGraduation(candidateEntity.getGraduation());
        candidateResponse.setPreference_of_atuation(candidateEntity.getPreference_of_atuation());
        candidateResponse.setCertification(candidateEntity.getCertification());
        candidateResponse.setLinkedin_profile(candidateEntity.getLinkedin_profile());

        return candidateResponse;

    }

    public CandidateEntity mapToRequest(CandidateRequest candidateRequest){
        CandidateEntity candidateEntity = new CandidateEntity();

        candidateEntity.setName(candidateRequest.getName());
        candidateEntity.setEmail(candidateRequest.getEmail());
        candidateEntity.setAge(candidateRequest.getAge());
        candidateEntity.setPosition_professional(candidateRequest.getPosition_professional());
        candidateEntity.setSeniority(candidateRequest.getSeniority());
        candidateEntity.setLocality(candidateRequest.getLocality());
        candidateEntity.setGraduation(candidateRequest.getGraduation());
        candidateEntity.setPreference_of_atuation(candidateRequest.getPreference_of_atuation());
        candidateEntity.setCertification(candidateRequest.getCertification());
        candidateEntity.setLinkedin_profile(candidateRequest.getLinkedin_profile());
        return candidateEntity;
    }
}
