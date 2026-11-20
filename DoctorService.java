package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<String> getAvailableSlots(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Doctor not found"));

        if (date == null) {
            throw new IllegalArgumentException("Date is required");
        }

        return doctor.getAvailableTimes();
    }

    public Doctor validateDoctorLogin(String email, String password) {
        if (email == null || email.isBlank() ||
                password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Email and password are required");
        }

        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("Doctor not found"));

        if (!org.springframework.security.crypto.password
                .PasswordEncoderFactories
                .createDelegatingPasswordEncoder()
                .matches(password, doctor.getPassword())) {
            throw new IllegalArgumentException("Invalid login credentials");
        }

        return doctor;
    }
}
