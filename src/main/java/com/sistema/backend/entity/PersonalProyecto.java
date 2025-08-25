package com.sistema.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(
    name = "PERSONAL_PROYECTO",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"personal_id", "proyecto_id"})}
)
public class PersonalProyecto {

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

    @Column(nullable = false, length = 100)
    private String rol; // Cargo específico en este proyecto

    @Column(length = 100)
    private String area; // Área específica del proyecto

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion = LocalDate.now();

    @Column(name = "fecha_desasignacion")
    private LocalDate fechaDesasignacion;

    @Column(nullable = false)
    private Boolean activo = true;

    // Campos laborales específicos
    @Column(name = "horas_laborales_semana")
    private Integer horasLaboralesSemana = 40;

    @Column(name = "salario_mensual", precision = 10, scale = 2)
    private BigDecimal salarioMensual;

    @Column(name = "tipo_contrato", length = 50)
    private String tipoContrato;

    @Column(name = "fecha_inicio_proyecto")
    private LocalDate fechaInicioProyecto;

    @Column(name = "fecha_fin_proyecto")
    private LocalDate fechaFinProyecto;

    @Column(name = "contrato_proyecto_url", columnDefinition = "TEXT")
    private String contratoProyectoUrl;

    @Column(name = "contrato_proyecto_nombre", length = 255)
    private String contratoProyectoNombre;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
