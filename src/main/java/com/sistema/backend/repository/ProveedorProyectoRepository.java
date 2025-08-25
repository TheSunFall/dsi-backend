package com.sistema.backend.repository;

import com.sistema.backend.entity.ProveedorProyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProveedorProyectoRepository extends JpaRepository<ProveedorProyecto, UUID> {
    List<ProveedorProyecto> findByProveedorId(UUID proveedorId);
    List<ProveedorProyecto> findByProyectoId(UUID proyectoId);
    Optional<ProveedorProyecto> findByProveedorIdAndProyectoId(UUID proveedorId, UUID proyectoId);
}
