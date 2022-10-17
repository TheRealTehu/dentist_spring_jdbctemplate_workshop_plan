insert into patients(name, gender, age) values ('Patient Name', 'MALE', 30);
insert into patients(name, gender, age) values ('Female Patient', 'FEMALE', 23);
insert into patients(name, gender, age) values ('Fogatlan Patient', 'FEMALE', 23);

insert into teeth(patient_id, tooth_type, is_filled) values (1, 'CANINE', false);
insert into teeth(patient_id, tooth_type, is_filled) values (1, 'MOLAR', false);
insert into teeth(patient_id, tooth_type, is_filled) values (1, 'INCISOR', true);

insert into teeth(patient_id, tooth_type, is_filled) values (2, 'INCISOR', true);
insert into teeth(patient_id, tooth_type, is_filled) values (2, 'INCISOR', true);
insert into teeth(patient_id, tooth_type, is_filled) values (2, 'INCISOR', true);