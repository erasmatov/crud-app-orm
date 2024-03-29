CREATE TABLE developers
(
    id           INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name   VARCHAR(20) NOT NULL,
    last_name    VARCHAR(30) NOT NULL,
    status       VARCHAR(7)  NOT NULL,
    specialty_id INT         NOT NULL,

    CONSTRAINT fk_developers_specialty_id FOREIGN KEY (specialty_id) REFERENCES specialties (id)
);