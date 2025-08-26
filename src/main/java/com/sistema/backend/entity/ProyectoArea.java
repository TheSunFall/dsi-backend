package com.sistema.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "PROYECTO_AREAS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"proyecto_id", "area", "cargo"})
})
public class ProyectoArea {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @Column(name = "area", nullable = false, length = 100)
    private String area;

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad = 1;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
