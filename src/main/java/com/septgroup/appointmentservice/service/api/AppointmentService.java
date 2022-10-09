package com.septgroup.appointmentservice.service.api;

import com.septgroup.appointmentservice.dto.Appointment;
import com.septgroup.appointmentservice.dto.container.Appointments;
import com.septgroup.appointmentservice.exception.InvalidIdException;
import com.septgroup.appointmentservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {
    Appointment getAppointment(String id) throws NotFoundException, InvalidIdException;

    Appointments getAppointmentsOfClinic(String clinicName);

    Appointments getAppointmentsOfDoctor(String doctorID);

    Appointments getAppointmentsOfPatient(String patientID);

    void addAppointment(Appointment newAppointment);

    void updateAppointment(Appointment newAppointment);

    void setAppointmentInactive(String id) throws InvalidIdException;

    void deleteAppointment(String id) throws InvalidIdException;
}
