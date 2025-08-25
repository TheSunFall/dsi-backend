package com.sistema.backend.repository;

import com.sistema.backend.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuditoriaRepository extends JpaRepository<Auditoria, UUID> {
    List<Auditoria> findByTabla(String tabla);
    List<Auditoria> findByRegistroId(String registroId);
    List<Auditoria> findByUsuario(String usuario);
}
