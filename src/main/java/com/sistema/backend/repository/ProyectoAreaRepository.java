package com.sistema.backend.repository;

import com.sistema.backend.entity.ProyectoArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProyectoAreaRepository extends JpaRepository<ProyectoArea, UUID> {
    List<ProyectoArea> findByProyectoId(UUID proyectoId);
    Optional<ProyectoArea> findByProyectoIdAndAreaAndCargo(UUID proyectoId, String area, String cargo);
}
