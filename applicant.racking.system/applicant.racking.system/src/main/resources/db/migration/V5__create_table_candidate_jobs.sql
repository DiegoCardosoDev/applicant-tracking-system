-- Create the join table for Candidate to Jobs (candidate_jobs)
CREATE TABLE candidate_jobs (
    candidate_id INT,
    job_id INT,
    PRIMARY KEY (candidate_id, job_id),
    FOREIGN KEY (candidate_id) REFERENCES candidate (id),
    FOREIGN KEY (job_id) REFERENCES jobs (id)
);