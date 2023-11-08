package com.api.applicant.racking.system.repositories;

import com.api.applicant.racking.system.entities.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<JobsEntity, Long> {
}
