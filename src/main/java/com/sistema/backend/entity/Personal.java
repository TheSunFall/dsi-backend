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
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO", unique = true, nullable = false, length = 20)
    private String codigo;

    @Column(name = "NOMBRES", nullable = false, length = 100)
    private String nombres;

    @Column(name = "APELLIDOS", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "DNI", unique = true, nullable = false, length = 8)
    private String dni;

    @Column(name = "TELEFONO", length = 15)
    private String telefono;

    @Column(name = "CORREO_INSTITUCIONAL", length = 100)
    private String correoInstitucional;

    @Column(name = "CORREO_PERSONAL", length = 100)
    private String correoPersonal;

    @Column(name = "DIRECCION", length = 255)
    private String direccion;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fechaNacimiento;

    @Column(name = "SEXO", length = 10)
    private String sexo;

    @Column(name = "FOTO_URL", length = 500)
    private String fotoUrl;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    // Getters y setters
}
