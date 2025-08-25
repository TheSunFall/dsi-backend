package com.sistema.backend.repository;

import com.sistema.backend.entity.PersonalTarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonalTareaRepository extends JpaRepository<PersonalTarea, UUID> {
    List<PersonalTarea> findByPersonalId(UUID personalId);
    List<PersonalTarea> findByProyectoId(UUID proyectoId);
    List<PersonalTarea> findByEstado(String estado);
}
