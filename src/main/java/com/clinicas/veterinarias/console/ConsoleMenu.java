package com.clinicas.veterinarias.console;

import com.clinicas.veterinarias.entity.*;
import com.clinicas.veterinarias.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleMenu implements CommandLineRunner {

    private final ClinicService clinicService;
    private final VeterinarianService veterinarianService;
    private final QuestionService questionService;
    private final MedicalRecordService medicalRecordService;
    private final PetService petService;

    @Autowired
    public ConsoleMenu(ClinicService clinicService,
                       VeterinarianService veterinarianService,
                       QuestionService questionService,
                       MedicalRecordService medicalRecordService, PetService petService) {
        this.clinicService = clinicService;
        this.veterinarianService = veterinarianService;
        this.questionService = questionService;
        this.medicalRecordService = medicalRecordService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar una clinica");
            System.out.println("2. Agregar un veterinario");
            System.out.println("3. Agregar una pregunta");
            System.out.println("4. Crear una ficha medica");
            System.out.println("5. Salir");
            System.out.print("Ingrese su eleccion: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1:
                    // Lógica para agregar una clínica
                    System.out.println("Ingrese el nombre de la clinica:");
                    String clinicName = scanner.nextLine();
                    System.out.println("Ingrese la ubicacion de la clinica:");
                    String clinicLocation = scanner.nextLine();

                    Clinic newClinic = new Clinic();
                    newClinic.setName(clinicName);
                    newClinic.setLocation(clinicLocation);

                    Clinic createdClinic = clinicService.createClinic(newClinic);
                    if (createdClinic != null) {
                        System.out.println("Clinica creada exitosamente.");
                    } else {
                        System.out.println("Error al crear la clinica. Intentelo de nuevo.");
                    }
                    break;
                case 2:
                    // Lógica para agregar un veterinario
                    System.out.println("Ingrese el nombre del veterinario:");
                    String vetName = scanner.nextLine();
                    System.out.println("Ingrese la especializacion del veterinario:");
                    String vetSpecialization = scanner.nextLine();

                    // Aquí necesitas el ID de la clínica a la que pertenece este veterinario
                    System.out.println("Ingrese el ID de la clinica para el veterinario:");
                    Long clinicIdForVet = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    Veterinarian newVet = new Veterinarian();
                    newVet.setName(vetName);
                    newVet.setSpecialization(vetSpecialization);

                    Clinic clinicForVet = clinicService.getClinicById(clinicIdForVet);
                    if (clinicForVet != null) {
                        newVet.setClinic(clinicForVet);
                        Veterinarian createdVet = veterinarianService.createVeterinarian(newVet);
                        if (createdVet != null) {
                            System.out.println("Veterinario creado exitosamente.");
                        } else {
                            System.out.println("Error al crear el veterinario. Intentelo de nuevo.");
                        }
                    } else {
                        System.out.println("No se encontro la clinica con el ID proporcionado.");
                    }
                    break;
                case 3:
                    // Lógica para agregar una pregunta
                    System.out.println("Ingrese el contenido de la pregunta:");
                    String questionContent = scanner.nextLine();

                    Question newQuestion = new Question();
                    newQuestion.setContent(questionContent);

                    Question createdQuestion = questionService.createQuestion(newQuestion);
                    if (createdQuestion != null) {
                        System.out.println("Pregunta creada exitosamente.");
                    } else {
                        System.out.println("Error al crear la pregunta. Intentelo de nuevo.");
                    }
                    break;
                case 4:
                    // Lógica para crear una ficha médica
                    System.out.println("Creando una ficha medica...");

                    // Obtener datos de la mascota
                    System.out.println("Ingrese el nombre de la mascota:");
                    String petName = scanner.nextLine();
                    System.out.println("Ingrese la edad de la mascota:");
                    int petAge = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    Pet newPet = new Pet();
                    newPet.setName(petName);
                    newPet.setAge(petAge);

                    // Obtener IDs necesarios para crear la ficha médica
                    System.out.println("Ingrese el ID del veterinario para la ficha medica:");
                    Long veterinarianId = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese el ID de la clinica para la ficha medica:");
                    Long clinicIdForRecord = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    // Obtener el veterinario y la clínica correspondientes a los IDs
                    Veterinarian veterinarianForRecord = veterinarianService.getVeterinarianById(veterinarianId);
                    Clinic clinicForRecord = clinicService.getClinicById(clinicIdForRecord);

                    if (veterinarianForRecord != null && clinicForRecord != null) {
                        MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(
                                veterinarianForRecord.getId(),
                                clinicForRecord.getId(),
                                List.of(newPet)
                        );
                        if (createdRecord != null) {
                            // Crear la mascota y asociarla a la ficha médica
                            Pet createdPet = petService.createPet(newPet);

                            if (createdPet != null) {
                                // Agregar la mascota a la ficha médica recién creada
                                createdPet.setMedicalRecord(createdRecord);
                                petService.updatePet(createdPet); // Actualizar la mascota con la ficha médica asociada

                                System.out.println("Ficha medica creada y mascota asociada exitosamente.");
                            } else {
                                System.out.println("Error al crear la mascota. Intentelo de nuevo.");
                            }
                        } else {
                            System.out.println("Error al crear la ficha medica. Intentelo de nuevo.");
                        }
                    } else {
                        System.out.println("No se encontro el veterinario o la clinica con los IDs proporcionados.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Por favor, ingrese una opcion valida.");
            }

        } while (choice != 5);
    }
}

