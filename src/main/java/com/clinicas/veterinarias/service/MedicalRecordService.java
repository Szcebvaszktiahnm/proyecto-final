package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.MedicalRecord;
import com.clinicas.veterinarias.entity.Pet;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecord createMedicalRecord(Long veterinarianId, Long clinicId, List<Pet> pets);
}
