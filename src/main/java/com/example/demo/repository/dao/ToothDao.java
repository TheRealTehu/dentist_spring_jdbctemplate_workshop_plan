package com.example.demo.repository.dao;

import com.example.demo.model.Tooth;

import java.util.List;

public interface ToothDao {
    public List<Tooth> getAllTeeth();
    public Tooth getToothById(long id);
    public List<Tooth> getAllTeethForPatientById(long patientId);
    public void addTooth(Tooth tooth);
    public void updateTooth(long id, Tooth tooth); // lehetne DTO
    public void deleteTooth(long id);
}