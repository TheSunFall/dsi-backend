package com.sistema.backend.repository;

import com.sistema.backend.entity.ExperienciaLaboral;
import com.sistema.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, UUID> {
    List<ExperienciaLaboral> findByPersonal(Personal personal);
}
