package com.sistema.backend.repository;

import com.sistema.backend.entity.PagoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PagoProveedorRepository extends JpaRepository<PagoProveedor, UUID> {
    List<PagoProveedor> findByProveedorId(UUID proveedorId);
    List<PagoProveedor> findByProyectoId(UUID proyectoId);
}
