package com.septgroup.appointmentservice.controller;

import com.septgroup.appointmentservice.dto.Appointment;
import com.septgroup.appointmentservice.service.api.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/clinic/{clinicName}")
    public ResponseEntity<Object> getAppointmentsOfClinic(@PathVariable("clinicName") String clinicName) {
        return ResponseEntity.ok(appointmentService.getAppointmentsOfClinic(clinicName));
    }

    @GetMapping("/doctor/{doctorID}")
    public ResponseEntity<Object> getAppointmentsOfDoctor(@PathVariable("doctorID") String doctorID) {
        return ResponseEntity.ok(appointmentService.getAppointmentsOfDoctor(doctorID));
    }

    @GetMapping("/patient/{patientID}")
    public ResponseEntity<Object> getAppointmentsOfPatient(@PathVariable("patientID") String patientID) {
        return ResponseEntity.ok(appointmentService.getAppointmentsOfPatient(patientID));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addAppointment(@RequestBody Appointment newAppointment) {
        appointmentService.addAppointment(newAppointment);
        // Set the location header field to the endpoint of this new appointment.
        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(newAppointment.getAppointmentID())
                .toUri();
        return ResponseEntity.created(loc).build();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Object> updateAppointment(@RequestBody Appointment newAppointment) {
        appointmentService.updateAppointment(newAppointment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{appointmentID}")
    public ResponseEntity<Object> setAppointmentInactive(@PathVariable("appointmentID") String appointmentID) {
        appointmentService.setAppointmentInactive(appointmentID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{appointmentID}")
    public ResponseEntity<Object> cancelAppointment(@PathVariable("appointmentID") String appointmentID) {
        Appointment appointment = appointmentService.getAppointment(appointmentID);
        appointmentService.deleteAppointment(appointmentID);
        return ResponseEntity.ok(appointment);
    }
}
