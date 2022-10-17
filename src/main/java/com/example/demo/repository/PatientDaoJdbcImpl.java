package com.example.demo.repository;

import com.example.demo.mapper.PatientMapper;
import com.example.demo.model.Gender;
import com.example.demo.model.Patient;
import com.example.demo.model.Tooth;
import com.example.demo.model.ToothType;
import com.example.demo.repository.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoJdbcImpl implements PatientDao {
    private JdbcTemplate template;
    private PatientMapper mapper;

    @Autowired
    public PatientDaoJdbcImpl(JdbcTemplate template, PatientMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public List<Patient> getAllPatients() {
        final String SQL = "SELECT patients.id, patients.name, patients.gender, patients.age, teeth.id, teeth.patient_id, teeth.tooth_type, teeth.is_filled " +
                "FROM patients LEFT JOIN teeth ON patients.id = teeth.patient_id;";
        List<Patient> patients = template.query(SQL, new ResultSetExtractor<List<Patient>>() {
            @Override
            public List<Patient> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Patient> patientList = new ArrayList<>();
                Patient actualPatient = new Patient();

                while(rs.next()){
                    if(actualPatient.getId() != null && rs.getLong("patients.id") == actualPatient.getId()){
                        Tooth tooth = getToothFromResultSet(rs);

                        actualPatient.addTooth(tooth);
                    } else {
                        if(actualPatient.getId() != null){
                            patientList.add(actualPatient);
                        }

                        actualPatient = getPatientFromResultSet(rs);

                        Tooth tooth = getToothFromResultSet(rs);

                        actualPatient.addTooth(tooth);
                    }
                }
                patientList.add(actualPatient);
                return patientList;
            }
        });
        return patients;
    }

    private Patient getPatientFromResultSet(ResultSet rs) throws SQLException {
        Patient actualPatient;
        actualPatient = new Patient();
        actualPatient.setId(rs.getLong("patients.id"));
        actualPatient.setName(rs.getString("patients.name"));
        actualPatient.setGender(Gender.valueOf(rs.getString("patients.gender")));
        actualPatient.setAge(rs.getInt("patients.age"));
        return actualPatient;
    }

    private Tooth getToothFromResultSet(ResultSet rs) throws SQLException {
        Tooth tooth = new Tooth();
        tooth.setId(rs.getLong("teeth.id"));
        tooth.setPatientId(rs.getLong("teeth.patient_id"));
        tooth.setToothType(ToothType.valueOf(rs.getString("teeth.tooth_type")));
        tooth.setFilled(rs.getBoolean("teeth.is_filled"));
        return tooth;
    }

    @Override
    public Patient getPatientById(long id) {
        final String SQL = "SELECT id, name, gender, age FROM patients WHERE id = ?";
        return template.queryForObject(SQL, mapper, id);
    }

    @Override
    public void addPatient(Patient patient) {
        final String SQL = "INSERT INTO patients(name, gender, age) VALUES(?,?,?);";
        Object[] args = new Object[]{patient.getName(), patient.getGender(), patient.getAge()};
        template.update(SQL, args);
    }

    @Override
    public void updatePatient(long id, Patient patient) {
        final String SQL = "UPDATE patients SET name = ?, gender = ?, age = ?;";
        Object[] args = new Object[]{patient.getName(), patient.getGender(), patient.getAge()};
        template.update(SQL, args);
    }

    @Override
    public void deletePatient(long id) {
        final String SQL = "DELETE FROM patients WHERE id = ?";
        template.update(SQL, id);
    }
}
