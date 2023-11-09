package com.api.applicant.racking.system.repositories;

import com.api.applicant.racking.system.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    List<CandidateEntity> findByNameContainingIgnoreCase(String name);
}
