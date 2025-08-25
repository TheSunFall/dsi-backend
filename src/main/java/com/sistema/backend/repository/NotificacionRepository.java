package com.sistema.backend.repository;

import com.sistema.backend.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificacionRepository extends JpaRepository<Notificacion, UUID> {
    List<Notificacion> findByPersonalId(UUID personalId);
    List<Notificacion> findByProyectoId(UUID proyectoId);
    List<Notificacion> findByProveedorId(UUID proveedorId);
    List<Notificacion> findByLeida(Boolean leida);
    List<Notificacion> findByTipo(String tipo);
}
