package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable("id") long id) {
        return service.getPatientById(id);
    }

    @PostMapping
    public void addPatient(@RequestBody Patient patient) {
        service.addPatient(patient);
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
        service.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") long id) {
        service.deletePatient(id);
    }
}
