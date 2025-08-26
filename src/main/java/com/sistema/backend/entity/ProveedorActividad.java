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
@Table(name = "PROVEEDOR_ACTIVIDADES")
public class ProveedorActividad {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    // Relación con proveedor_proyecto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedor_proyecto_id", nullable = false)
    private ProveedorProyecto proveedorProyecto;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "fecha_ejecucion", nullable = false)
    private LocalDate fechaEjecucion;

    @Column(length = 50, nullable = false)
    private String estado = "En proceso"; // Ejecutado, En proceso, Reprogramado, Cancelado

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "documento_pdf_url")
    private String documentoPdfUrl;

    @Column(name = "documento_pdf_nombre")
    private String documentoPdfNombre;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
