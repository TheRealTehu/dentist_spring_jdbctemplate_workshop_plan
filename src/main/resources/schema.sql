drop table IF EXISTS teeth CASCADE;
create TABLE teeth
(
    id                  bigint PRIMARY KEY AUTO_INCREMENT,
    patient_id          bigint NOT NULL,
    tooth_type          VARCHAR(10) check (tooth_type in ('INCISOR', 'CANINE', 'MOLAR')),
    is_filled           boolean
);

drop table IF EXISTS patients CASCADE;
create TABLE patients
(
    id                  bigint PRIMARY KEY AUTO_INCREMENT,
    name                VARCHAR(50) NOT NULL,
    gender              VARCHAR(10) check (gender in ('MALE', 'FEMALE')),
    age                 int
);

ALTER TABLE teeth
    ADD FOREIGN KEY (patient_id)
    REFERENCES patients(id)
    ON DELETE CASCADE;