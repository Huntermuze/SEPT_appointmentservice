package com.septgroup.appointmentservice.controller;

import com.septgroup.appointmentservice.dto.Appointment;
import com.septgroup.appointmentservice.dto.container.Appointments;
import com.septgroup.appointmentservice.service.api.AppointmentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AppointmentControllerTest {
    @MockBean
    private AppointmentService appointmentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAppointmentsOfPatientTest() throws Exception {
        // given
        String patientID = "ae7c4877-2ccd-4965-8d80-ef67321d6921";
        List<Appointment> appointmentList = List.of(
                new Appointment(4, "Halil Land", "2022-04-05T08:40#P20M", "Patellar Tendonitis check-up",
                        "ki7c43417-2ccd-4965-8d80-ef67321d6921", patientID, false),
                new Appointment(34, "Halil Valley", "2022-04-05T08:40#P20M", "Ankle check-up",
                        "ki7c43417-2ccd-4965-8d80-ef67321d6921", patientID, false)
        );
        Appointments appointments = new Appointments(appointmentList);
        // when
        appointmentService.getAppointmentsOfPatient(patientID);
        Mockito.when(appointmentService.getAppointmentsOfPatient(patientID)).thenReturn(appointments);
        // then
        Mockito.verify(appointmentService).getAppointmentsOfPatient(patientID);
        mockMvc.perform(MockMvcRequestBuilders.get("/appointments/patient/{patientID}", patientID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appointmentList", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.appointmentList[0].clinicName", Matchers.is("Halil Land")));
    }

    @Test
    public void getAppointmentsOfDoctorTest() throws Exception {
        // given
        String doctorID = "lf7c4877-2ccd-4965-8d80-ef67321d6921";
        List<Appointment> appointmentList = List.of(
                new Appointment(4, "Halil Land", "2022-04-05T08:40#P20M", "Patellar Tendonitis check-up",
                        doctorID, "ae7c4877-2ccd-4965-8d80-ef67321d6921", false),
                new Appointment(34, "Halil Valley", "2022-04-05T08:40#P20M", "Ankle check-up",
                        doctorID, "ki7c43417-2ccd-4965-8d80-ef67321d6921", false)
        );
        Appointments appointments = new Appointments(appointmentList);
        // when
        appointmentService.getAppointmentsOfDoctor(doctorID);
        Mockito.when(appointmentService.getAppointmentsOfDoctor(doctorID)).thenReturn(appointments);
        // then
        Mockito.verify(appointmentService).getAppointmentsOfDoctor(doctorID);
        mockMvc.perform(MockMvcRequestBuilders.get("/appointments/doctor/{doctorID}", doctorID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appointmentList", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.appointmentList[0].clinicName", Matchers.is("Halil Land")));
    }

    @Test
    public void deleteAppointmentTest() throws Exception {
        // given
        String apptId = "19";
        // when
        appointmentService.deleteAppointment(apptId);
        // then
        Mockito.verify(appointmentService).deleteAppointment(apptId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/appointments/{appointmentID}", apptId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
