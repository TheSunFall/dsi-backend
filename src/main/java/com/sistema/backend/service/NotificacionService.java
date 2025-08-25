package com.sistema.backend.service;

import com.sistema.backend.entity.Notificacion;
import com.sistema.backend.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<Notificacion> getAll() {
        return repository.findAll();
    }

    public Notificacion getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<Notificacion> getByPersonal(UUID personalId) {
        return repository.findByPersonalId(personalId);
    }

    public List<Notificacion> getByProyecto(UUID proyectoId) {
        return repository.findByProyectoId(proyectoId);
    }

    public List<Notificacion> getByProveedor(UUID proveedorId) {
        return repository.findByProveedorId(proveedorId);
    }

    public List<Notificacion> getByLeida(boolean leida) {
        return repository.findByLeida(leida);
    }

    public List<Notificacion> getByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public Notificacion save(Notificacion notificacion) {
        return repository.save(notificacion);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
