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
@Table(name = "PAGOS_PERSONAL")
public class PagoPersonal {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    // Relación con Personal
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    // Relación opcional con Proyecto
    @ManyToOne(fetch = FetchType.EAGER)
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
    private String concepto; // Salario, bono, etc.

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago; // Efectivo, transferencia, etc.

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
