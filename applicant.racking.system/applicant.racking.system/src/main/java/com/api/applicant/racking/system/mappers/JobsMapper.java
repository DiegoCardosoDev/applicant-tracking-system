package com.api.applicant.racking.system.mappers;


import com.api.applicant.racking.system.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobsMapper {

    private final CandidateRepository candidateRepository;
    private final TechnologyStackRepository technologyStackRepository;
    private final JobsRepository jobsRepository;
    private final ExperienceRepository experienceRepository;
    private final EnterPriseRepository enterPriseRepository;
}
