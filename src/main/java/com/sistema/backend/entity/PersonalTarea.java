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
@Table(name = "PERSONAL_TAREAS")
public class PersonalTarea {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    // Relación con Personal
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    // Relación con Proyecto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @Column(nullable = false, length = 200)
    private String tarea;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion = LocalDate.now();

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFinalizacion;

    @Column(length = 50)
    private String estado = "Pendiente";

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
