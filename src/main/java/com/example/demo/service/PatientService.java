package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientDao patientDao;

    @Autowired
    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    public Patient getPatientById(long id) {
        return patientDao.getPatientById(id);
    }

    public void addPatient(Patient patient) {
        patientDao.addPatient(patient);
    }

    public void updatePatient(long id, Patient patient) {
        patientDao.updatePatient(id, patient);
    }

    public void deletePatient(long id) {
        patientDao.deletePatient(id);
    }

}
