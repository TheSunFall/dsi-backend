package com.sistema.backend.repository;

import com.sistema.backend.entity.Especialidad;
import com.sistema.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EspecialidadRepository extends JpaRepository<Especialidad, UUID> {
    List<Especialidad> findByPersonal(Personal personal);
}