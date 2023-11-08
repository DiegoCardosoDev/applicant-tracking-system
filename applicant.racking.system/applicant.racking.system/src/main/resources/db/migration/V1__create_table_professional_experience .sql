CREATE TABLE professional_experience (
    id SERIAL PRIMARY KEY,
    enterprise_name VARCHAR(255),
    locality VARCHAR(255),
    entry_date DATE,
    exit_date DATE,
    duration INTEGER
);
