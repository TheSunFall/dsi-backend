package com.sistema.backend.repository;

import com.sistema.backend.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    Optional<Personal> findByDni(String dni);
    Optional<Personal> findByCodigo(String codigo);
}
