package com.example.demo.mapper;

import com.example.demo.model.Gender;
import com.example.demo.model.Patient;
import com.example.demo.repository.dao.ToothDao;
import com.example.demo.service.ToothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PatientMapper implements RowMapper<Patient> {
    private ToothService toothService;

    @Autowired
    public PatientMapper(ToothService toothService) {
        this.toothService = toothService;
    }

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getLong("id"));
        patient.setName(rs.getString("name"));
        patient.setGender(Gender.valueOf(rs.getString("gender")));
        patient.setAge(rs.getInt("age"));
        patient.setTeeth(toothService.getAllTeethForPatientById(patient.getId()));

        return patient;
    }
}
