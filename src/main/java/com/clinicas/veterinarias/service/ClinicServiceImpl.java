package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Clinic;
import com.clinicas.veterinarias.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Clinic createClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    @Override
    public Clinic updateClinic(Long clinicId, Clinic updatedClinic) {
        Optional<Clinic> existingClinicOptional = clinicRepository.findById(clinicId);
        if (existingClinicOptional.isPresent()) {
            Clinic existingClinic = existingClinicOptional.get();
            existingClinic.setName(updatedClinic.getName());
            existingClinic.setLocation(updatedClinic.getLocation());

            return clinicRepository.save(existingClinic);
        }
        return null;
    }

    @Override
    public void deleteClinic(Long clinicId) {
        clinicRepository.deleteById(clinicId);
    }

    @Override
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic getClinicById(Long clinicIdForVet) {
        return clinicRepository.findById(clinicIdForVet).orElse(null);
    }
}