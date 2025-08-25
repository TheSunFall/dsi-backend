package com.sistema.backend.repository;

import com.sistema.backend.entity.EvaluacionDesempeno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EvaluacionDesempenoRepository extends JpaRepository<EvaluacionDesempeno, UUID> {
    Optional<EvaluacionDesempeno> findByPersonalIdAndProyectoId(UUID personalId, UUID proyectoId);
    List<EvaluacionDesempeno> findByPersonalId(UUID personalId);
    List<EvaluacionDesempeno> findByProyectoId(UUID proyectoId);
}
