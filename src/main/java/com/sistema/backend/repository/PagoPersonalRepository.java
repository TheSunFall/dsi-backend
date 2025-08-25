package com.sistema.backend.repository;

import com.sistema.backend.entity.PagoPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PagoPersonalRepository extends JpaRepository<PagoPersonal, UUID> {
    List<PagoPersonal> findByPersonalId(UUID personalId);
    List<PagoPersonal> findByProyectoId(UUID proyectoId);
}
