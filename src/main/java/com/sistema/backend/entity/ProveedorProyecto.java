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
    name = "PROVEEDOR_PROYECTO",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"proveedor_id", "proyecto_id"})}
)
public class ProveedorProyecto {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    // Relación con Proveedor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    // Relación con Proyecto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @Column(length = 200)
    private String servicio;

    @Column(name = "servicio_descripcion", columnDefinition = "TEXT")
    private String servicioDescripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "monto_total_contratado", precision = 12, scale = 2)
    private BigDecimal montoTotalContratado;

    @Column(name = "fecha_contrato")
    private LocalDate fechaContrato;

    @Column(length = 50)
    private String estado = "Activo";

    @Column(name = "contrato_pdf_url", columnDefinition = "TEXT")
    private String contratoPdfUrl;

    @Column(name = "contrato_pdf_nombre", length = 255)
    private String contratoPdfNombre;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
