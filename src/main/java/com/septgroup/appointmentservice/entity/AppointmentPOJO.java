package com.septgroup.appointmentservice.entity;

import javax.persistence.*;

@Entity(name = "Appointment")
@Table(name = "Appointment")
public class AppointmentPOJO {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long appointmentID;
    @Column(name = "clinic_id", nullable = false)
    private String clinicName;
    @Column(name = "timeslot", nullable = false)
    private String timeslot;
    @Column(name = "description")
    private String description;
    @Column(name = "patient_id", nullable = false)
    private String patientID;
    @Column(name = "doctor_id", nullable = false)
    private String doctorID;
    @Column(name = "active", nullable = false)
    private boolean active;

    public AppointmentPOJO(Long appointmentID, String clinicName, String timeslot, String description, String patientID, String doctorID, boolean active) {
        this.appointmentID = appointmentID;
        this.clinicName = clinicName;
        this.timeslot = timeslot;
        this.description = description;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.active = active;
    }

    public AppointmentPOJO() {

    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
