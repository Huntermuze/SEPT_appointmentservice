package com.septgroup.appointmentservice.service.implementation;

import com.septgroup.appointmentservice.dto.Appointment;
import com.septgroup.appointmentservice.dto.Clinic;
import com.septgroup.appointmentservice.dto.container.Appointments;
import com.septgroup.appointmentservice.entity.AppointmentPOJO;
import com.septgroup.appointmentservice.exception.AlreadyExistException;
import com.septgroup.appointmentservice.exception.InvalidIdException;
import com.septgroup.appointmentservice.exception.NotFoundException;
import com.septgroup.appointmentservice.repository.AppointmentRepository;
import com.septgroup.appointmentservice.service.AppointmentMergeService;
import com.septgroup.appointmentservice.service.api.AppointmentService;
import com.septgroup.appointmentservice.util.VerificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMergeService appointmentMergeService;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Appointment getAppointment(String id) throws NotFoundException, InvalidIdException {
        long appointmentID = VerificationUtil.ifValidGetApptID(id);
        var result = appointmentRepository.findById(appointmentID);
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The appointment with id %s does not exist!", appointmentID));
        }

        return appointmentMergeService.AppointmentEntityToDTO(result.get());
    }

    @Override
    public Appointments getAppointmentsOfClinic(String clinicName) {
        List<Appointment> appointmentList = new ArrayList<>();
        for (AppointmentPOJO a : appointmentRepository.findAllAppointmentsByClinic(clinicName)) {
            appointmentList.add(appointmentMergeService.AppointmentEntityToDTO(a));
        }
        return new Appointments(appointmentList);
    }

    @Override
    public Appointments getAppointmentsOfDoctor(String doctorID) throws InvalidIdException {
        VerificationUtil.throwIfNotValidUUID(doctorID);
        List<Appointment> appointmentList = new ArrayList<>();
        for (AppointmentPOJO a : appointmentRepository.findAllAppointmentsByDoctor(doctorID)) {
            appointmentList.add(appointmentMergeService.AppointmentEntityToDTO(a));
        }
        return new Appointments(appointmentList);
    }

    @Override
    public Appointments getAppointmentsOfPatient(String patientID) throws InvalidIdException {
        VerificationUtil.throwIfNotValidUUID(patientID);
        List<Appointment> appointmentList = new ArrayList<>();
        for (AppointmentPOJO a : appointmentRepository.findAllAppointmentsByPatient(patientID)) {
            appointmentList.add(appointmentMergeService.AppointmentEntityToDTO(a));
        }
        return new Appointments(appointmentList);
    }

    @Override
    public void addAppointment(Appointment newAppointment) throws AlreadyExistException {
        try {
            restTemplate.getForObject("http://accounts-service/accountinfo/clinic/" + newAppointment.getClinicName(), Clinic.class);
        } catch (RestClientException e) {
            throw new NotFoundException(String.format("The clinic (%s) does not exist!", newAppointment.getClinicName()));
        }
        VerificationUtil.throwIfNotValidUUID(newAppointment.getPatientID());
        VerificationUtil.throwIfNotValidUUID(newAppointment.getDoctorID());
        // Cannot force the client to pick the unique ids. This is the database's job.
        var results = appointmentRepository.findAppointmentByFieldValues(newAppointment.getClinicName(),
                newAppointment.isActive(), newAppointment.getDescription(), newAppointment.getDoctorID(),
                newAppointment.getPatientID(), newAppointment.getTimeslot().toString());
        if (results.isPresent()) {
            throw new AlreadyExistException("This appointment has already been scheduled!");
        }
        appointmentRepository.save(appointmentMergeService.AppointmentDTOToEntity(newAppointment));
    }

    @Override
    public void updateAppointment(Appointment newAppointment) throws NotFoundException {
        try {
            restTemplate.getForObject("http://accounts-service/accountinfo/clinic/" + newAppointment.getClinicName(), Clinic.class);
        } catch (RestClientException e) {
            throw new NotFoundException(String.format("The clinic (%s) does not exist!", newAppointment.getClinicName()));
        }
        VerificationUtil.throwIfNotValidUUID(newAppointment.getPatientID());
        VerificationUtil.throwIfNotValidUUID(newAppointment.getDoctorID());
        // Cannot force the client to pick the unique ids. This is the database's job.
        var results = appointmentRepository.findById(newAppointment.getAppointmentID());
        if (results.isEmpty()) {
            throw new NotFoundException(String.format("The appointment with id %s does not exist!", newAppointment.getAppointmentID()));
        }
        appointmentRepository.save(appointmentMergeService.AppointmentDTOToEntity(newAppointment));
    }

    @Override
    public void setAppointmentInactive(String id) throws InvalidIdException, NotFoundException {
        long appointmentID = VerificationUtil.ifValidGetApptID(id);
        var result = appointmentRepository.findById(appointmentID);
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The appointment with id %s does not exist!", id));
        }
        AppointmentPOJO appt = result.get();
        appt.setActive(false);
        appointmentRepository.save(appt);
    }

    @Override
    public void deleteAppointment(String id) throws InvalidIdException {
        long appointmentID = VerificationUtil.ifValidGetApptID(id);
        var result = appointmentRepository.findById(appointmentID);
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The appointment with id %s does not exist!", id));
        }
        appointmentRepository.delete(result.get());
    }
}
