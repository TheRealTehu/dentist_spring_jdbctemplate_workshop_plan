package com.example.demo.repository;

import com.example.demo.mapper.ToothMapper;
import com.example.demo.model.Tooth;
import com.example.demo.model.ToothType;
import com.example.demo.repository.dao.ToothDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToothDaoJdbcImpl implements ToothDao {

    private JdbcTemplate template;
    private ToothMapper mapper;

    @Autowired
    public ToothDaoJdbcImpl(JdbcTemplate template, ToothMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public Connection getConnection() {
        Connection connection = null;
        String userName = "tehu";
        String password = "teszt";
        String host = "localhost";
        String databaseName = "dentist_db";


        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + host + ":5432/" + databaseName, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Tooth> getAllTeethOldVersion(){
        try(Connection connection = getConnection()){
            final String SQL = "SELECT id, patient_id, tooth_type, is_filled FROM teeth;";
            PreparedStatement st = connection.prepareCall(SQL);
            ResultSet rs = st.executeQuery();
            List<Tooth> teeth = new ArrayList<>();
            while (rs.next()){
                Tooth tooth = new Tooth(rs.getLong(1), rs.getLong(2), ToothType.valueOf(rs.getString(3)), rs.getBoolean(4));
                teeth.add(tooth);
            }

            return teeth;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tooth> getAllTeeth() { //megvalósítani try catch - connectionnel
        final String SQL = "SELECT id, patient_id, tooth_type, is_filled FROM teeth;";
        return template.query(SQL, mapper);
    }

    @Override
    public Tooth getToothById(long id) {
        final String SQL = "SELECT id, patient_id, tooth_type, is_filled FROM teeth WHERE id = ?;";
        return template.queryForObject(SQL, mapper, id);
    }

    @Override
    public List<Tooth> getAllTeethForPatientById(long patientId) {
        final String SQL = "SELECT id, patient_id, tooth_type, is_filled FROM teeth WHERE patient_id = ?;";
        return template.query(SQL, mapper, patientId);
    }

    @Override
    public void addTooth(Tooth tooth) { // hogyan szedjük vissza a generált ID-t?
        final String SQL = "INSERT INTO teeth(patient_id, tooth_type, is_filled) VALUES (?,?,?);";
        Object[] args = new Object[]{
                tooth.getPatientId(),
                tooth.getToothType().name(),
                tooth.isFilled()
        };

        template.update(SQL, args);
    }
    @Override
    public Tooth addToothAndReturnNewTooth(Tooth tooth){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO teeth(patient_id, tooth_type, is_filled) VALUES (?,?,?);";

        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, tooth.getPatientId());
            statement.setString(2, tooth.getToothType().toString());
            statement.setBoolean(3, tooth.isFilled());
            return statement;
        }, keyHolder);

        return getToothById(keyHolder.getKey().longValue());
    }

    @Override
    public void updateTooth(long id, Tooth tooth) {
        final String SQL = "UPDATE teeth SET patient_id = ?, tooth_type = ?, is_filled = ? WHERE id = ?;";
        Object[] args = new Object[]{
                tooth.getPatientId(),
                tooth.getToothType().name(),
                tooth.isFilled(),
                id
        };

        template.update(SQL, args);
    }

    @Override
    public void deleteTooth(long id) {
        final String SQL = "DELETE FROM teeth WHERE id = ?;";
        template.update(SQL, id);
    }
}