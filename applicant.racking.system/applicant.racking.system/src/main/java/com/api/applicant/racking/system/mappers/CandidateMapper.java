package com.api.applicant.racking.system.mappers;


import com.api.applicant.racking.system.dto.requests.CandidateRequest;
import com.api.applicant.racking.system.dto.responses.CandidateResponse;
import com.api.applicant.racking.system.dto.responses.CourseResponse;
import com.api.applicant.racking.system.dto.responses.JobsResponse;
import com.api.applicant.racking.system.dto.responses.TechnologyStackResponse;
import com.api.applicant.racking.system.entities.*;
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
        candidateResponse.setPhone(candidateEntity.getPhone());
        candidateResponse.setAge(candidateEntity.getAge());
        candidateResponse.setPosition_professional(candidateEntity.getPosition_professional());
        candidateResponse.setSeniority(candidateEntity.getSeniority());
        candidateResponse.setLocality(candidateEntity.getLocality());
        candidateResponse.setGraduation(candidateEntity.getGraduation());
        candidateResponse.setPreference_of_atuation(candidateEntity.getPreference_of_atuation());
        candidateResponse.setCertification(candidateEntity.getCertification());
        candidateResponse.setLinkedin_profile(candidateEntity.getLinkedin_profile());


        //anos e meses de experiencia do candidato
        int totalMonths = candidateEntity.getMonths_length_of_experience();
        int totalYears = candidateEntity.getYears_length_of_experience();

        if (totalMonths >= 12) {
            int yearsFromMonths = totalMonths / 12;
            totalMonths %= 12;
            totalYears += yearsFromMonths;
        }

        String lengh = totalYears + " ano(s) " + totalMonths + " mês(es)";
        candidateResponse.setLength_of_experience(lengh);


        //pegar as stack do candidato
        List<TechnologyStackResponse> stackResponses = candidateEntity.getStack().stream()
                .map(stack -> {
                    TechnologyStackResponse stackResponse = new TechnologyStackResponse();
                    stackResponse.setId(stack.getId());
                    stackResponse.setTechnology_name(stack.getTechnology_name());
                    return stackResponse;
                })
                .collect(Collectors.toList());
        candidateResponse.setStacks(stackResponses);

        //vagas que eo candidato esta aplicado
        List<JobsResponse> jobsResponses = candidateEntity.getJobs()
                .stream()
                .map(jobsEntity -> {
                    JobsResponse jobsResponse = new JobsResponse();
                    jobsResponse.setJob_title(jobsEntity.getJob_title());
                    jobsResponse.setRemote_converted(jobsEntity.getIs_remote() ? "SIM" : "NÃO");
                    jobsResponse.setIs_hibrid_converted(jobsEntity.getIs_hybrid() ? "SIM" : "NÃO");
                    jobsResponse.setIs_active_converted(jobsEntity.getIs_active() ? "SIM" : "NÃO");
                    jobsResponse.setType_contract(jobsEntity.getType_contract());
                    jobsResponse.setSeniority(jobsEntity.getSeniority());
                    jobsResponse.setSalary_proposal(jobsEntity.getSalary_proposal());
                    jobsResponse.setLocality(jobsEntity.getLocality());
                    return jobsResponse;
                })
                .collect(Collectors.toList());
        candidateResponse.setApplied(jobsResponses);

        //cursos
        List<CourseResponse> courseResponseList = candidateEntity.getCoursesEntities()
                .stream()
                .map(courseEntity -> {
                    CourseResponse courseResponse = new CourseResponse();
                    courseResponse.setCourse_name(courseEntity.getCourse_name());
                    courseResponse.setCourse_instituition(courseEntity.getCourse_instituition());
                    courseResponse.setDuration(courseEntity.getDuration());
                    return courseResponse;
                }).collect(Collectors.toList());
        candidateResponse.setCourses(courseResponseList);

        return candidateResponse;

    }

    public CandidateEntity mapToRequest(CandidateRequest candidateRequest){
        CandidateEntity candidateEntity = new CandidateEntity();

        candidateEntity.setName(candidateRequest.getName());
        candidateEntity.setEmail(candidateRequest.getEmail());
        candidateEntity.setPhone(candidateRequest.getPhone());
        candidateEntity.setAge(candidateRequest.getAge());
        candidateEntity.setPosition_professional(candidateRequest.getPosition_professional());
        candidateEntity.setSeniority(candidateRequest.getSeniority());
        candidateEntity.setLocality(candidateRequest.getLocality());
        candidateEntity.setGraduation(candidateRequest.getGraduation());
        candidateEntity.setPreference_of_atuation(candidateRequest.getPreference_of_atuation());
        candidateEntity.setCertification(candidateRequest.getCertification());
        candidateEntity.setLinkedin_profile(candidateRequest.getLinkedin_profile());


        //setar as experiencias do candidato
        List<ProfessionalExperienceEntity> experiences = candidateRequest.getExperiences().stream()
                .map(expRequest -> {
                    ProfessionalExperienceEntity experience = new ProfessionalExperienceEntity();
                    experience.setEnterprise_name(expRequest.getEnterprise_name());
                    experience.setLocality(expRequest.getLocality());
                    experience.setEntry_date(expRequest.getEntry_date());
                    experience.setExit_date(expRequest.getExit_date());

                    // Calcule a diferença de tempo em dias e armazene em um campo Integer
                    long entryDateMillis = expRequest.getEntry_date().toEpochDay();
                    long exitDateMillis = expRequest.getExit_date().toEpochDay();
                    int daysDifference = (int) (exitDateMillis - entryDateMillis);
                    experience.setDuration(daysDifference);
                    return experienceRepository.save(experience);
                })
                .collect(Collectors.toList());
        candidateEntity.setProfessionalExperienceEntities(experiences);


        // pegar o tempo de experiencia do candidato
        int totalYears = experiences.stream()
                .map(experience -> {
                    Period period = Period.between(experience.getEntry_date(), experience.getExit_date());
                    int totalMonths = period.getYears() * 12 + period.getMonths();  // Convertendo anos em meses
                    return totalMonths / 12;  // Convertendo meses em anos
                })
                .mapToInt(Integer::intValue)
                .sum();

        int remainingMonths = experiences.stream()
                .map(experience -> {
                    Period period = Period.between(experience.getEntry_date(), experience.getExit_date());
                    int totalMonths = period.getYears() * 12 + period.getMonths();  // Convertendo anos em meses
                    return totalMonths % 12;  // Obtendo o restante como meses
                })
                .mapToInt(Integer::intValue)
                .sum();

        candidateEntity.setYears_length_of_experience(totalYears);
        candidateEntity.setMonths_length_of_experience(remainingMonths);

        //ursos do candidato
        List<CourseEntity> savedCourses = candidateRequest.getCourses().stream()
                .map(courseRequest -> {
                    CourseEntity courseEntity = new CourseEntity();
                    courseEntity.setCourse_name(courseRequest.getCourse_name());
                    courseEntity.setCourse_instituition(courseRequest.getCourse_instituition());
                    courseEntity.setDuration(courseRequest.getDuration());
                    // Salve o curso no banco de dados
                    return courseRepository.save(courseEntity);
                })
                .collect(Collectors.toList());
        List<Long> stackIds = candidateRequest.getStack();
        List<TechnologyStackEntity> technologyStackEntities = this.technologyStackRepository.findAllById(stackIds);
        candidateEntity.setStack(technologyStackEntities);

        List<Long> aplyJobs = candidateRequest.getJobs();
        List<JobsEntity> jobsEntities = this.jobsRepository.findAllById(aplyJobs);
        candidateEntity.setJobs(jobsEntities);
        candidateEntity.setCoursesEntities(savedCourses);



        candidateEntity = candidateRepository.save(candidateEntity);
        return candidateEntity;
    }
}
