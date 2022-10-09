package com.septgroup.appointmentservice.repository;

import com.septgroup.appointmentservice.entity.AppointmentPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentPOJO, Long> {
    @Query(value = "SELECT * FROM Appointment a WHERE a.clinic_id = ?1", nativeQuery = true)
    List<AppointmentPOJO> findAllAppointmentsByClinic(String clinicName);

    @Query(value = "SELECT * FROM Appointment a WHERE a.doctor_id = ?1", nativeQuery = true)
    List<AppointmentPOJO> findAllAppointmentsByDoctor(String doctorID);

    @Query(value = "SELECT * FROM Appointment a WHERE a.patient_id = ?1", nativeQuery = true)
    List<AppointmentPOJO> findAllAppointmentsByPatient(String patientID);

    @Query(value = "SELECT * FROM Appointment a WHERE a.patient_id = ?1 AND a.active = true", nativeQuery = true)
    List<AppointmentPOJO> findAllActiveAppointmentsByPatient(String patientID);

    @Query(value = "SELECT * FROM Appointment a WHERE a.clinic_id = ?1 AND a.active = ?2 AND a.description = ?3 AND a.doctor_id = ?4 AND a.patient_id = ?5 AND a.timeslot = ?6", nativeQuery = true)
    Optional<AppointmentPOJO> findAppointmentByFieldValues(String clinicName, boolean active, String description, String doctorID, String patientID, String timeslot);
}
