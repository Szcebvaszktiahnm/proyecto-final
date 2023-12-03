package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Veterinarian;
import com.clinicas.veterinarias.repository.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinarianServiceImpl implements VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    @Autowired
    public VeterinarianServiceImpl(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public Veterinarian createVeterinarian(Veterinarian veterinarian) {
        return veterinarianRepository.save(veterinarian);
    }

    @Override
    public Veterinarian updateVeterinarian(Long veterinarianId, Veterinarian updatedVeterinarian) {
        Optional<Veterinarian> existingVetOptional = veterinarianRepository.findById(veterinarianId);
        if (existingVetOptional.isPresent()) {
            Veterinarian existingVet = existingVetOptional.get();
            existingVet.setName(updatedVeterinarian.getName());
            existingVet.setSpecialization(updatedVeterinarian.getSpecialization());
            // Puedes agregar más campos para actualizar según tu modelo
            return veterinarianRepository.save(existingVet);
        }
        return null; // O manejar este caso según tu lógica de negocio
    }

    @Override
    public void deleteVeterinarian(Long veterinarianId) {
        veterinarianRepository.deleteById(veterinarianId);
    }

    @Override
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.findAll();
    }

    @Override
    public Veterinarian getVeterinarianById(Long veterinarianId) {
        return veterinarianRepository.findById(veterinarianId).orElse(null);
    }
}

