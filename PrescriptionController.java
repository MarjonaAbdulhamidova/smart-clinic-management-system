package com.smartclinic.controller;

import com.smartclinic.model.Prescription;
import com.smartclinic.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<?> createPrescription(
            @Valid @RequestBody Prescription prescription,
            @RequestHeader(value = "Authorization", required = false)
            String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Valid authentication token is required");
        }

        if (prescription == null ||
                prescription.getMedication() == null ||
                prescription.getMedication().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Prescription medication is required");
        }

        try {
            Prescription savedPrescription =
                    prescriptionService.createPrescription(prescription);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedPrescription);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
