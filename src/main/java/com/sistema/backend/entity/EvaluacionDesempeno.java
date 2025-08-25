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
@Table(
    name = "EVALUACIONES_DESEMPENO",
    uniqueConstraints = @UniqueConstraint(columnNames = {"personal_id", "proyecto_id"})
)
public class EvaluacionDesempeno {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    // Relación con Personal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    // Relación con Proyecto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @Column(name = "calidad_trabajo")
    private Integer calidadTrabajo;

    @Column(name = "habilidades_comunicacion")
    private Integer habilidadesComunicacion;

    @Column
    private Integer personalidad;

    @Column(name = "conocimiento_trabajo")
    private Integer conocimientoTrabajo;

    @Column
    private Integer confiabilidad;

    @Column
    private Integer productividad;

    @Column(name = "cumplimiento_metas")
    private Integer cumplimientoMetas;

    @Column(name = "contribucion_equipo")
    private Integer contribucionEquipo;

    @Column
    private Integer puntualidad;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @Column(name = "fecha_evaluacion")
    private LocalDate fechaEvaluacion = LocalDate.now();

    @Column
    private String evaluador;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
