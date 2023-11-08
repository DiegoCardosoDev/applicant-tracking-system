CREATE TABLE jobs (
    id SERIAL PRIMARY KEY,
    job_title VARCHAR(255),
    locality VARCHAR(255),
    is_remote BOOLEAN,
    salary_proposal NUMERIC,
    is_active BOOLEAN,
    open_date DATE,
    close_date DATE,
    type_contract VARCHAR(255),
    seniority VARCHAR(255),
    is_hybrid BOOLEAN,
    is_mandatory_training BOOLEAN,
     duration_days INT,
    enterprise_id BIGINT,
    FOREIGN KEY (enterprise_id) REFERENCES enterprise(id)
);

