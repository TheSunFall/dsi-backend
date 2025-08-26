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
@Table(name = "DATOS_LABORALES")
public class DatosLaborales {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    @Column(name = "area", length = 100)
    private String area;

    @Column(name = "cargo", length = 100)
    private String cargo;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "tipo_contrato", length = 50)
    private String tipoContrato;

    @Column(name = "horas_laborales")
    private Integer horasLaborales;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "contrato_url", columnDefinition = "TEXT")
    private String contratoUrl;

    @Column(name = "contrato_nombre", length = 255)
    private String contratoNombre;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
