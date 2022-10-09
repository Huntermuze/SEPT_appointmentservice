package com.septgroup.appointmentservice.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Appointment {
    private final long appointmentID;
    private String clinicName;
    private String timeslot;
    private String description;
    private String patientID;
    private String doctorID;
    private boolean active;

    public Appointment(long appointmentID, String clinicName, String timeslot, String description, String patientID, String doctorID, boolean active) {
        this.appointmentID = appointmentID;
        this.clinicName = clinicName;
        this.timeslot = timeslot;
        this.description = description;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.active = active;
    }

    public boolean hasAppointmentExpired() {
        Timeslot slot = Timeslot.getTimeslotFromString(timeslot);
        return Instant.now().getNano() > slot.getStart().plusSeconds(slot.getLengthOfSlot().getSeconds()).getNano();
    }

    public long getAppointmentID() {
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

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID=" + appointmentID +
                ", clinicName='" + clinicName + '\'' +
                ", timeslot=" + timeslot +
                ", description='" + description + '\'' +
                ", patientID='" + patientID + '\'' +
                ", doctorID='" + doctorID + '\'' +
                ", active=" + active +
                '}';
    }
}
