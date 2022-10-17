package com.example.demo.service;

import com.example.demo.model.Tooth;
import com.example.demo.repository.dao.ToothDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToothService {
    private ToothDao repository;

    @Autowired
    public ToothService(ToothDao repository) {
        this.repository = repository;
    }

    public List<Tooth> getAllTeeth(){
        return repository.getAllTeeth();
    }

    public Tooth getToothById(long id){
        return repository.getToothById(id);
    }

    public List<Tooth> getAllTeethForPatientById(long patientId){
        return repository.getAllTeethForPatientById(patientId);
    }

    public void addTooth(Tooth tooth){
        repository.addTooth(tooth);
    }

    public Tooth addToothAndReturnNewTooth(Tooth tooth){
        return repository.addToothAndReturnNewTooth(tooth);
    }

    public void updateTooth(long id, Tooth tooth) {
        repository.updateTooth(id, tooth);
    }

    public void deleteTooth(long id){
        repository.deleteTooth(id);
    }
}