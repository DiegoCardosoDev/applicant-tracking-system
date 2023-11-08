package com.api.applicant.racking.system.repositories;

import com.api.applicant.racking.system.entities.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterPriseRepository extends JpaRepository<EnterpriseEntity, Long> {
}
