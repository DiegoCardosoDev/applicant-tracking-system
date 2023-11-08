package com.api.applicant.racking.system.repositories;

import com.api.applicant.racking.system.entities.ProfessionalExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<ProfessionalExperienceEntity, Long> {
}
