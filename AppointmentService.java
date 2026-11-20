package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import com.smartclinic.model.Doctor;
import com.smartclinic.repository.AppointmentRepository;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public Appointment bookAppointment(Appointment appointment) {
        if (appointment == null ||
                appointment.getDoctor() == null ||
                appointment.getPatient() == null ||
                appointment.getAppointmentTime() == null) {
            throw new IllegalArgumentException(
                    "Doctor, patient, and appointment time are required");
        }

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsForDoctorOnDate(
            Long doctorId, LocalDate date) {

        if (!doctorRepository.existsById(doctorId)) {
            throw new IllegalArgumentException("Doctor not found");
        }

        return appointmentRepository
                .findByDoctorIdAndAppointmentTimeBetween(
                        doctorId,
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay()
                );
    }
}
