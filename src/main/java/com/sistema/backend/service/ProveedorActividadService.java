package com.sistema.backend.service;

import com.sistema.backend.entity.ProveedorActividad;
import com.sistema.backend.repository.ProveedorActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProveedorActividadService {

    private final ProveedorActividadRepository repository;

    public ProveedorActividadService(ProveedorActividadRepository repository) {
        this.repository = repository;
    }

    public List<ProveedorActividad> getAll() {
        return repository.findAll();
    }

    public Optional<ProveedorActividad> getById(UUID id) {
        return repository.findById(id);
    }

    public List<ProveedorActividad> getByProveedorProyecto(UUID proveedorProyectoId) {
        return repository.findByProveedorProyectoId(proveedorProyectoId);
    }

    public List<ProveedorActividad> getByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public ProveedorActividad save(ProveedorActividad actividad) {
        return repository.save(actividad);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
