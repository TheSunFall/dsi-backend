package com.sistema.backend.repository;

import com.sistema.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
    Optional<Personal> findByDni(String dni);
    Optional<Personal> findByCodigo(String codigo);
}
