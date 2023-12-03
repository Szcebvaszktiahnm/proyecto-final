package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Pet;
import com.clinicas.veterinarias.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        // Asegúrate de que la pet tenga un ID existente para actualizarla en la base de datos
        if (pet.getId() == null) {
            throw new IllegalArgumentException("La mascota no tiene un ID válido.");
        }
        return petRepository.save(pet);
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }



}

