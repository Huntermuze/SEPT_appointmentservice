package com.septgroup.appointmentservice.dto.container;

import com.septgroup.appointmentservice.dto.Appointment;
import reactor.util.annotation.NonNull;

import java.util.List;

public class Appointments {
    private final List<Appointment> appointmentList;

    public Appointments(@NonNull List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
