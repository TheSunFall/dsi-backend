package com.sistema.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "PERSONAL")
public class Personal {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "codigo", unique = true, nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "dni", unique = true, nullable = false, length = 8)
    private String dni;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "correo_institucional", unique = true, length = 100)
    private String correoInstitucional;

    @Column(name = "correo_personal", length = 100)
    private String correoPersonal;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "domicilio", columnDefinition = "TEXT")
    private String domicilio;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "lugar_nacimiento", length = 100)
    private String lugarNacimiento;

    @Column(name = "estado_civil", length = 20)
    private String estadoCivil;

    @Column(name = "sexo", length = 10)
    private String sexo;

    @Column(name = "foto_url", columnDefinition = "TEXT")
    private String fotoUrl;

    @Column(name = "licencia_conducir", length = 20)
    private String licenciaConducir;

    @Column(name = "hobby", columnDefinition = "TEXT")
    private String hobby;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
