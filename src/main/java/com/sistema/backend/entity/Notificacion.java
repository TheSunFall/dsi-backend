package com.sistema.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "NOTIFICACIONES")
public class Notificacion {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 50)
    private String tipo; // contrato, actividad, asignacion, tarea...

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_evento", nullable = false)
    private OffsetDateTime fechaEvento;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion = OffsetDateTime.now();

    @Column(nullable = false)
    private Boolean leida = false;

    @Column(nullable = false, length = 20)
    private String prioridad = "media"; // alta, media, baja

    // Relaciones opcionales
    @Column(name = "personal_id")
    private UUID personalId;

    @Column(name = "proveedor_id")
    private UUID proveedorId;

    @Column(name = "proyecto_id")
    private UUID proyectoId;

    // Campo JSONB (lo representamos como String en Java, se puede mapear con librerías avanzadas)
    @Column(name = "datos_adicionales", columnDefinition = "jsonb")
    private String datosAdicionales;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
