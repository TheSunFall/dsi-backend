package com.sistema.backend.repository;

import com.sistema.backend.entity.PersonalProyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonalProyectoRepository extends JpaRepository<PersonalProyecto, UUID> {
    List<PersonalProyecto> findByProyectoId(UUID proyectoId);
    List<PersonalProyecto> findByPersonalId(UUID personalId);
    Optional<PersonalProyecto> findByPersonalIdAndProyectoId(UUID personalId, UUID proyectoId);
}
