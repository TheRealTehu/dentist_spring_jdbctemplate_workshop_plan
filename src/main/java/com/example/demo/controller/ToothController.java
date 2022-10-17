package com.example.demo.controller;

import com.example.demo.model.Tooth;
import com.example.demo.service.ToothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tooth")
public class ToothController {
    private final ToothService service;

    @Autowired
    public ToothController(ToothService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Tooth> getAllTeeth(){
        return service.getAllTeeth();
    }

    @GetMapping("/{id}")
    public Tooth getToothById(@PathVariable("id") long id){
        return service.getToothById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<Tooth> getAllTeethForPatientById(@PathVariable("patientId") long patientId){
        return service.getAllTeethForPatientById(patientId);
    }

    @PostMapping
    public void addTooth(@RequestBody Tooth tooth){
        service.addTooth(tooth);
    }

    @PostMapping("/return")
    public Tooth addToothAndReturnNewTooth(@RequestBody Tooth tooth){
        return service.addToothAndReturnNewTooth(tooth);
    }

    @PutMapping("/{id}")
    public void updateTooth(@PathVariable("id") long id, @RequestBody Tooth tooth){
        service.updateTooth(id, tooth);
    }

    @DeleteMapping("/{id}")
    public void deleteTooth(@PathVariable("id") long id){
        service.deleteTooth(id);
    }
}