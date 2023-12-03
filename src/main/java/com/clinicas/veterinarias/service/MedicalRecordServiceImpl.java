package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Clinic;
import com.clinicas.veterinarias.entity.MedicalRecord;
import com.clinicas.veterinarias.entity.Pet;
import com.clinicas.veterinarias.entity.Veterinarian;
import com.clinicas.veterinarias.repository.ClinicRepository;
import com.clinicas.veterinarias.repository.MedicalRecordRepository;
import com.clinicas.veterinarias.repository.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final ClinicRepository clinicRepository;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository,
                                    VeterinarianRepository veterinarianRepository,
                                    ClinicRepository clinicRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.clinicRepository = clinicRepository;
    }

    @Override
    public MedicalRecord createMedicalRecord(Long veterinarianId, Long clinicId, List<Pet> pets) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDate(new Date());

        // Obtener veterinario y clínica por ID
        Veterinarian veterinarian = veterinarianRepository.findById(veterinarianId).orElse(null);
        Clinic clinic = clinicRepository.findById(clinicId).orElse(null);

        if (veterinarian != null && clinic != null) {
            medicalRecord.setVeterinarian(veterinarian);
            medicalRecord.setClinic(clinic);
            medicalRecord.setPets(pets);
            // Guardar el registro médico
            return medicalRecordRepository.save(medicalRecord);
        }
        return null; // O manejar este caso según tu lógica de negocio
    }
}
