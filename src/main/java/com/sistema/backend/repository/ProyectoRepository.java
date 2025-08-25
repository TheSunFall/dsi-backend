package com.sistema.backend.repository;

import com.sistema.backend.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProyectoRepository extends JpaRepository<Proyecto, UUID> {
    Optional<Proyecto> findByCodigo(String codigo);
}
