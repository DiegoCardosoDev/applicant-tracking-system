package com.api.applicant.racking.system.services;

import com.api.applicant.racking.system.dto.requests.JobsRequest;
import com.api.applicant.racking.system.dto.responses.JobsResponse;
import com.api.applicant.racking.system.entities.JobsEntity;
import com.api.applicant.racking.system.mappers.JobsMapper;
import com.api.applicant.racking.system.repositories.CandidateRepository;
import com.api.applicant.racking.system.repositories.JobsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Um serviço que fornece operações relacionadas a vagas de emprego.
 */
@Service
@RequiredArgsConstructor
public class JobsService {

    private final JobsRepository jobsRepository;
    private final CandidateRepository candidateRepository;
    private final JobsMapper jobsMapper;

    /**
     * Cria e salva uma nova vaga.
     *
     * @param jobsRequest Os dados da vaga a ser criada.
     * @return A resposta com os detalhes da vaga criada.
     */
    public JobsResponse createNewJob(JobsRequest jobsRequest) {
        JobsEntity jobsEntity = jobsMapper.mapJobToRequest(jobsRequest);
        jobsEntity.setIs_active(true);
        jobsEntity.setOpen_date(LocalDate.now());
        JobsEntity entity = jobsRepository.save(jobsEntity);
        return jobsMapper.mapToResponse(entity);
    }

    /**
     * Lista todas as vagas.
     *
     * @return Uma lista de respostas com os detalhes de todas as vagas.
     */
    public List<JobsResponse> getAllJobs() {
        List<JobsEntity> candidateEntities = jobsRepository.findAll();
        return candidateEntities
                .stream()
                .map(jobsMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<JobsResponse> getJobByTitle(String name){
        List<JobsEntity> jobs = jobsRepository.findByJobTitleContainingIgnoreCase(name);
        return jobs.stream()
                .map(jobsMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza uma vaga existente.
     *
     * @param jobId O ID da vaga a ser atualizada.
     * @param jobsRequest Os novos dados da vaga.
     * @return A resposta com os detalhes da vaga atualizada.
     */
    public JobsResponse updateJob(Long jobId, JobsRequest jobsRequest) {
        Optional<JobsEntity> existingJob = jobsRepository.findById(jobId);

        if (existingJob.isPresent()) {
            JobsEntity jobEntity = jobsMapper.mapJobToRequest(jobsRequest);
            jobEntity.setId(jobId);
            jobEntity.setIs_active(true);
            // Obter a data 'open_date' da entidade existente
            LocalDate existingOpenDate = existingJob.get().getOpen_date();

            // Definir a data 'open_date' na nova entidade
            jobEntity.setOpen_date(existingOpenDate);

            JobsEntity updatedJobEntity = jobsRepository.save(jobEntity);
            JobsResponse updatedJobResponse = jobsMapper.mapToResponse(updatedJobEntity); // Mapeie a entidade atualizada para a resposta
            return updatedJobResponse;
        } else {
            // Lida com o caso em que o ID do trabalho não foi encontrado
            throw new EntityNotFoundException("Job with ID " + jobId + " not found");
        }
    }

    /**
     * Deleta uma vaga pelo ID.
     *
     * @param id O ID da vaga a ser deletada.
     */
    public void deleteJobsId(Long id) {
        if (!jobsRepository.existsById(id)) {
            throw new EntityNotFoundException("Job not found with ID: " + id);
        }

        jobsRepository.deleteById(id);
    }

    public List<JobsResponse> getAllActiveJobs() {
        List<JobsEntity> activeJobs = jobsRepository.findActiveJobsIsTrue(); // Substitua pelo método correto em seu repositório
        List<JobsResponse> activeJobResponses = activeJobs.stream()
                .map(jobsMapper::mapToResponse)
                .collect(Collectors.toList());
        return activeJobResponses;
    }

    /**
     * Desativa uma vaga pelo ID.
     *
     * @param id O ID da vaga a ser desativada.
     */
    public void deactivateJob(Long id) {
        Optional<JobsEntity> jobOptional = jobsRepository.findById(id);

        if (jobOptional.isPresent()) {
            JobsEntity job = jobOptional.get();

            if (!job.getIs_active()) {
                throw new IllegalStateException("Job with ID " + id + " is already deactivated");
            }

            job.setIs_active(false);
            job.setClose_date(LocalDate.now());

            LocalDate openDate = job.getOpen_date();
            LocalDate closeDate = job.getClose_date();

            if (openDate != null && closeDate != null) {
                LocalDateTime openDateTime = openDate.atStartOfDay();
                LocalDateTime closeDateTime = closeDate.atStartOfDay();
                Duration duration = Duration.between(openDateTime, closeDateTime);
                long durationInDays = duration.toDays();

                // Converta para Integer
                Integer durationInDaysInteger = Math.toIntExact(durationInDays);
                System.out.println(durationInDaysInteger);

                job.setDuration_days(durationInDaysInteger);
            } else {
                // Trate o caso em que as datas estão ausentes
            }

            jobsRepository.save(job);
        } else {
            throw new EntityNotFoundException("Job with ID " + id + " not found");
        }
    }
}
