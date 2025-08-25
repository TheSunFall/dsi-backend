package com.sistema.backend.repository;

import com.sistema.backend.entity.ProveedorActividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProveedorActividadRepository extends JpaRepository<ProveedorActividad, UUID> {
    List<ProveedorActividad> findByProveedorProyectoId(UUID proveedorProyectoId);
    List<ProveedorActividad> findByEstado(String estado);
}
