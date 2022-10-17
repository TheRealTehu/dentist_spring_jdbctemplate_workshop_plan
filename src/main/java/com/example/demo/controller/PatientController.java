package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return service.getAllPatients();
    }


    public Patient getPatientById(long id) {
        return service.getPatientById(id);
    }

    public void addPatient(Patient patient) {
        service.addPatient(patient);
    }

    public void updatePatient(long id, Patient patient) {
        service.updatePatient(id, patient);
    }

    public void deletePatient(long id) {
        service.deletePatient(id);
    }
}
