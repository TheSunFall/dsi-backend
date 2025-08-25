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
@Table(name = "EXPERIENCIA_LABORAL")
public class ExperienciaLaboral {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    @Column(name = "institucion", nullable = false, length = 200)
    private String institucion;

    @Column(name = "area", nullable = false, length = 100)
    private String area;

    @Column(name = "especialidad", nullable = false, length = 200)
    private String especialidad;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "anos_experiencia")
    private Integer anosExperiencia;

    @Column(name = "certificado_url", columnDefinition = "TEXT")
    private String certificadoUrl;

    @Column(name = "certificado_nombre", length = 255)
    private String certificadoNombre;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
