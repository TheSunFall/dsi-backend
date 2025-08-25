package com.sistema.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "PAGOS_PROVEEDOR")
public class PagoProveedor {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    // Relación con Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    // Relación opcional con Proyecto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @Column(name = "fecha_hora", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime fechaHora = OffsetDateTime.now();

    @Column(name = "numero_operacion", length = 100, nullable = false)
    private String numeroOperacion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "boleta_url", columnDefinition = "TEXT")
    private String boletaUrl;

    @Column(name = "boleta_nombre", length = 255)
    private String boletaNombre;

    @Column(name = "boleta_pdf_url", columnDefinition = "TEXT")
    private String boletaPdfUrl;

    @Column(name = "boleta_pdf_nombre", columnDefinition = "TEXT")
    private String boletaPdfNombre;

    @Column(length = 200)
    private String concepto; // servicios, materiales, etc.

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago; // efectivo, transferencia, etc.

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
