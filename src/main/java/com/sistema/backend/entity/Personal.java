package com.sistema.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PERSONAL")
@SequenceGenerator(name = "personal_seq", sequenceName = "PERSONAL_SEQ", allocationSize = 1)
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_seq")
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    private String telefono;
    private String correoInstitucional;
    private String correoPersonal;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String fotoUrl;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Getters y setters
}
