package com.sistema.backend.repository;

import com.sistema.backend.entity.GradoAcademico;
import com.sistema.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GradoAcademicoRepository extends JpaRepository<GradoAcademico, UUID> {
    List<GradoAcademico> findByPersonal(Personal personal);
}
