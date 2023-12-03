package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Pet;

public interface PetService {
    Pet createPet(Pet pet);
    Pet updatePet(Pet pet);
    Pet getPetById(Long id);

}

