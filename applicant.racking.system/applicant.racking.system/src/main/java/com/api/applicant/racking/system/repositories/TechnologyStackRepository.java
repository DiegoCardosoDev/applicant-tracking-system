package com.api.applicant.racking.system.repositories;

import com.api.applicant.racking.system.entities.TechnologyStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyStackRepository extends JpaRepository<TechnologyStackEntity, Long> {
}
