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
@Table(name = "GRADOS_ACADEMICOS")
public class GradoAcademico {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    @Column(name = "carrera", nullable = false, length = 200)
    private String carrera;

    @Column(name = "universidad", nullable = false, length = 200)
    private String universidad;

    @Column(name = "fecha_graduacion")
    private LocalDate fechaGraduacion;

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
