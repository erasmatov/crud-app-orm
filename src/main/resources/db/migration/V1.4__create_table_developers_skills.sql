CREATE TABLE developers_skills
(
    developer_id INT REFERENCES developers (id) NOT NULL,
    skill_id     INT REFERENCES skills (id)     NOT NULL,


    CONSTRAINT fk_dev_skills_dev_id FOREIGN KEY (developer_id) REFERENCES developers (id),
    CONSTRAINT fk_dev_skills_skill_id FOREIGN KEY (skill_id) REFERENCES skills (id)
);