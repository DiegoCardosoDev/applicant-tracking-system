package com.api.applicant.racking.system.repositories;

import com.api.applicant.racking.system.entities.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<JobsEntity, Long> {

    @Query ("SELECT j FROM JobsEntity j WHERE j.is_active = true")
    List<JobsEntity> findActiveJobsIsTrue();

    @Query("SELECT j FROM JobsEntity j WHERE j.job_title = :jobTitle")
    List<JobsEntity> findByJobTitle(String jobTitle);


}
