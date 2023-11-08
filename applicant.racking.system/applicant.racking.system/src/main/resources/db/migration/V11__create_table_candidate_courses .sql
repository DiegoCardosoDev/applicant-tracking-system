CREATE TABLE candidate_courses (
    candidate_id BIGINT,
    courses_id BIGINT,
    FOREIGN KEY (candidate_id) REFERENCES candidate(id),
    FOREIGN KEY (courses_id) REFERENCES courses(id)
);