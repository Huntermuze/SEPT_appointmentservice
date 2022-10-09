package com.septgroup.appointmentservice.service;

import com.septgroup.appointmentservice.dto.Appointment;
import com.septgroup.appointmentservice.entity.AppointmentPOJO;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMergeService {
    public AppointmentPOJO AppointmentDTOToEntity(Appointment appointment) {
        return new AppointmentPOJO(appointment.getAppointmentID(), appointment.getClinicName(),
                appointment.getTimeslot(), appointment.getDescription(), appointment.getPatientID(), appointment.getDoctorID(),
                appointment.isActive());
    }

    public Appointment AppointmentEntityToDTO(AppointmentPOJO appointmentPOJO) {
        return new Appointment(appointmentPOJO.getAppointmentID(), appointmentPOJO.getClinicName(), appointmentPOJO.getTimeslot(),
                appointmentPOJO.getDescription(), appointmentPOJO.getPatientID(), appointmentPOJO.getDoctorID(),
                appointmentPOJO.isActive());
    }
}
