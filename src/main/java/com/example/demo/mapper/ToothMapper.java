package com.example.demo.mapper;

import com.example.demo.model.Tooth;
import com.example.demo.model.ToothType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ToothMapper implements RowMapper<Tooth> {
    @Override
    public Tooth mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tooth tooth = new Tooth();
        tooth.setId(rs.getLong("id"));
        tooth.setPatientId(rs.getLong("patient_id"));
        tooth.setToothType(ToothType.valueOf(rs.getString("tooth_type")));
        tooth.setFilled(rs.getBoolean("is_filled"));
        return tooth;
    }
}
