CREATE TABLE candidate_stack (
    candidate_id INT,
    stack_id INT,
    PRIMARY KEY (candidate_id, stack_id),
    FOREIGN KEY (candidate_id) REFERENCES candidate (id),
    FOREIGN KEY (stack_id) REFERENCES stack (id)
);