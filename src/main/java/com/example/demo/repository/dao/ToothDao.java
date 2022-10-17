package com.example.demo.repository.dao;

import com.example.demo.model.Tooth;

import java.util.List;

public interface ToothDao {
    List<Tooth> getAllTeeth();

    Tooth getToothById(long id);

    List<Tooth> getAllTeethForPatientById(long patientId);

    void addTooth(Tooth tooth);

    Tooth addToothAndReturnNewTooth(Tooth tooth);

    void updateTooth(long id, Tooth tooth); // lehetne DTO

    void deleteTooth(long id);
}