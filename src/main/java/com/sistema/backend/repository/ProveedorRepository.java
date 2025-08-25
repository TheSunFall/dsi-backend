package com.sistema.backend.repository;

import com.sistema.backend.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProveedorRepository extends JpaRepository<Proveedor, UUID> {
    Optional<Proveedor> findByCodigo(String codigo);
    Optional<Proveedor> findByRuc(String ruc);
}