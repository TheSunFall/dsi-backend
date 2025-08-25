package com.sistema.backend.repository;

import com.sistema.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonalRepository extends JpaRepository<Personal, UUID> {
    Optional<Personal> findByCodigo(String codigo);
    Optional<Personal> findByDni(String dni);
}
