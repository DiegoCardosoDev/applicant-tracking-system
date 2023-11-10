package com.api.applicant.racking.system.mappers;


import com.api.applicant.racking.system.dto.requests.JobsRequest;
import com.api.applicant.racking.system.dto.responses.JobsResponse;
import com.api.applicant.racking.system.entities.EnterpriseEntity;
import com.api.applicant.racking.system.entities.JobsEntity;
import com.api.applicant.racking.system.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Component
public class JobsMapper {

    private  CandidateRepository candidateRepository;
    private  TechnologyStackRepository technologyStackRepository;
    private  JobsRepository jobsRepository;
    private  ExperienceRepository experienceRepository;
    private  EnterPriseRepository enterPriseRepository;

    public JobsMapper(CandidateRepository candidateRepository,TechnologyStackRepository technologyStackRepository,
                      JobsRepository jobsRepository,ExperienceRepository experienceRepository,EnterPriseRepository enterPriseRepository){
        this.candidateRepository = candidateRepository;
        this.technologyStackRepository = technologyStackRepository;
        this.jobsRepository = jobsRepository;
        this.experienceRepository = experienceRepository;
        this.enterPriseRepository = enterPriseRepository;
    }

    public JobsResponse mapToResponse(JobsEntity jobsEntity) {



        DecimalFormat currencyFormatted =
                new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));


        JobsResponse jobsResponse = new JobsResponse();

        jobsResponse.setJob_title(jobsEntity.getJob_title());
        jobsResponse.setLocality(jobsEntity.getLocality());

        Double salary = jobsEntity.getSalary_proposal();
        String formattedSalary = currencyFormatted.format(salary);
        jobsResponse.setSalary_converted(formattedSalary);
        jobsResponse.setJob_title(jobsEntity.getJob_title());
        jobsResponse.setLocality(jobsEntity.getLocality());
        jobsResponse.setIs_remote(jobsEntity.getIs_remote());
        jobsResponse.setIs_active(jobsEntity.getIs_active());
        jobsResponse.setSeniority(jobsEntity.getSeniority());
        jobsResponse.setType_contract(jobsEntity.getType_contract());
        jobsResponse.setIs_mandatory_training(jobsEntity.getIs_mandatory_training());

        // Tratar campos nulos
        jobsResponse.setOpen_date(jobsEntity.getOpen_date() != null
                ? jobsEntity.getOpen_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "-");

        // Adicionar mapeamento para close_date
        jobsResponse.setClose_date(jobsEntity.getClose_date() != null
                ? jobsEntity.getClose_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "-");
        // Proteja contra valores nulos nas propriedades booleanas
        jobsResponse.setIs_active_converted(jobsEntity.getIs_active() != null && jobsEntity.getIs_active() ? "SIM" : "NÃO");
        jobsResponse.setIs_hibrid_converted(jobsEntity.getIs_hybrid() != null && jobsEntity.getIs_hybrid() ? "SIM" : "NÃO");
        jobsResponse.setIs_mandatory_training_converted(jobsEntity.getIs_mandatory_training() != null && jobsEntity.getIs_mandatory_training() ? "SIM" : "NÃO");
        jobsResponse.setRemote_converted(jobsEntity.getIs_remote() != null && jobsEntity.getIs_remote() ? "SIM" : "NÃO");
        jobsResponse.setDuration_inDays(jobsEntity.getDuration_days() != null ? jobsEntity.getDuration_days()  + " dia(s)" : 0 + " dias(s)");

        Optional<EnterpriseEntity> enterpriseOptional = enterPriseRepository.findById(jobsEntity.getEnterprise().getId());
        EnterpriseEntity enterprise = enterpriseOptional.get();
        String name = enterprise.getName(); // Substitua "name" pelo campo correto em ProfessionalExperience
        jobsResponse.setEnterprise(name);
        return jobsResponse;
    }

    public JobsEntity mapJobToRequest(JobsRequest jobsRequest){
        JobsEntity jobEntity = new JobsEntity();
        jobEntity.setJob_title(jobsRequest.getJob_title());
        jobEntity.setLocality(jobsRequest.getLocality());
        jobEntity.setSalary_proposal(jobsRequest.getSalary_proposal());
        jobEntity.setIs_remote(jobsRequest.getIs_remote());
        jobEntity.setIs_mandatory_training(jobsRequest.getIs_mandatory_training());
        jobEntity.setIs_hybrid(jobsRequest.getIs_hybrid());
        jobEntity.setType_contract(jobsRequest.getType_contract());
        jobEntity.setSeniority(jobsRequest.getSeniority());
        jobEntity.setIs_active(jobsRequest.getIs_active());

        Long enterpriseId = jobsRequest.getEnterprise_id();

        Optional<EnterpriseEntity> enterpriseOptional = enterPriseRepository.findById(enterpriseId);

        // Verificar se o Optional contém a empresa e, se sim, extrair o valor
        if (enterpriseOptional.isPresent()) {
            EnterpriseEntity enterprise = enterpriseOptional.get();

            // Definir a empresa no objeto jobEntity
            jobEntity.setEnterprise(enterprise);
        } else {
            throw new EntityNotFoundException("Enterprise with ID " + enterpriseId + " not found");
        }

        return jobEntity;
    }



}
