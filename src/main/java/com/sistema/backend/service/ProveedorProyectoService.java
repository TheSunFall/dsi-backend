package com.sistema.backend.service;

import com.sistema.backend.entity.ProveedorProyecto;
import com.sistema.backend.repository.ProveedorProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProveedorProyectoService {

    private final ProveedorProyectoRepository proveedorProyectoRepository;

    public ProveedorProyectoService(ProveedorProyectoRepository proveedorProyectoRepository) {
        this.proveedorProyectoRepository = proveedorProyectoRepository;
    }

    public List<ProveedorProyecto> getAll() {
        return proveedorProyectoRepository.findAll();
    }

    public List<ProveedorProyecto> getByProveedorId(UUID proveedorId) {
        return proveedorProyectoRepository.findByProveedorId(proveedorId);
    }

    public List<ProveedorProyecto> getByProyectoId(UUID proyectoId) {
        return proveedorProyectoRepository.findByProyectoId(proyectoId);
    }

    public Optional<ProveedorProyecto> getById(UUID id) {
        return proveedorProyectoRepository.findById(id);
    }

    public Optional<ProveedorProyecto> getByProveedorAndProyecto(UUID proveedorId, UUID proyectoId) {
        return proveedorProyectoRepository.findByProveedorIdAndProyectoId(proveedorId, proyectoId);
    }

    public ProveedorProyecto save(ProveedorProyecto proveedorProyecto) {
        return proveedorProyectoRepository.save(proveedorProyecto);
    }

    public void delete(UUID id) {
        proveedorProyectoRepository.deleteById(id);
    }
}
