package com.sistema.backend.repository;

import com.sistema.backend.entity.Proveedor;
import com.sistema.backend.entity.ProveedorServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProveedorServicioRepository extends JpaRepository<ProveedorServicio, UUID> {
    List<ProveedorServicio> findByProveedor(Proveedor proveedor);
}
