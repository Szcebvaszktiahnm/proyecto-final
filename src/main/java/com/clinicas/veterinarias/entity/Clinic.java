package com.clinicas.veterinarias.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;
    private String location;

    @OneToMany(mappedBy = "clinic")
    private List<Veterinarian> veterinarians;

}
