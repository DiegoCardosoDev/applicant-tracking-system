-- Cria a tabela de relacionamento many-to-many com EnterpriseEntity
CREATE TABLE candidate_experience (
    candidate_id BIGINT,
    professional_experience_id BIGINT,
    FOREIGN KEY (candidate_id) REFERENCES candidate(id),
    FOREIGN KEY (professional_experience_id) REFERENCES professional_experience(id)
);
