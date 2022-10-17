package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tooth {
    private long id;
    private long patientId;
    private ToothType toothType;
    private boolean isFilled;
}