package com.sistema.backend.repository;

import com.sistema.backend.entity.DatosLaborales;
import com.sistema.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DatosLaboralesRepository extends JpaRepository<DatosLaborales, UUID> {
    List<DatosLaborales> findByPersonal(Personal personal);
}
