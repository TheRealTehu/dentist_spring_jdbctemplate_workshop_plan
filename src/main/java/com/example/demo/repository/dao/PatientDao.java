package com.example.demo.repository.dao;

import com.example.demo.model.Patient;

import java.util.List;

public interface PatientDao {
    List<Patient> getAllPatients();
    Patient getPatientById(long id);
    void addPatient(Patient patient); //Lehetne DTO
    void updatePatient(long id, Patient patient);
    void deletePatient(long id);

}
