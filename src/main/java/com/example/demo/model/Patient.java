package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Patient {
    private Long id;
    private String name;
    private Gender gender;
    private int age;
    private List<Tooth> teeth;

    public Patient() {
        teeth = new ArrayList<>();
    }

    public void addTooth(Tooth tooth) {
        teeth.add(tooth);
    }
}
