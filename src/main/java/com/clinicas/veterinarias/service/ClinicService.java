package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(Clinic clinic);
    Clinic updateClinic(Long clinicId, Clinic updatedClinic);
    void deleteClinic(Long clinicId);
    List<Clinic> getAllClinics();

    Clinic getClinicById(Long clinicIdForVet);
}
