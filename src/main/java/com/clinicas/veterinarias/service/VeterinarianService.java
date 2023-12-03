package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Veterinarian;

import java.util.List;

public interface VeterinarianService {
    Veterinarian createVeterinarian(Veterinarian veterinarian);
    Veterinarian updateVeterinarian(Long veterinarianId, Veterinarian updatedVeterinarian);
    void deleteVeterinarian(Long veterinarianId);
    List<Veterinarian> getAllVeterinarians();
    Veterinarian getVeterinarianById(Long veterinarianId);
}
