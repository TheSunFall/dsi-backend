package com.sistema.backend.service;

import com.sistema.backend.entity.ProveedorServicio;
import com.sistema.backend.repository.ProveedorRepository;
import com.sistema.backend.repository.ProveedorServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProveedorServicioService {

    private final ProveedorServicioRepository proveedorServicioRepository;
    private final ProveedorRepository proveedorRepository;

    public ProveedorServicioService(ProveedorServicioRepository proveedorServicioRepository, ProveedorRepository proveedorRepository) {
        this.proveedorServicioRepository = proveedorServicioRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<ProveedorServicio> getAll() {
        return proveedorServicioRepository.findAll();
    }

    public Optional<ProveedorServicio> getById(UUID id) {
        return proveedorServicioRepository.findById(id);
    }

    public List<ProveedorServicio> getByProveedorId(UUID proveedorId) {
        return proveedorRepository.findById(proveedorId)
                .map(proveedorServicioRepository::findByProveedor)
                .orElse(List.of());
    }

    public ProveedorServicio save(ProveedorServicio servicio) {
        return proveedorServicioRepository.save(servicio);
    }

    public void delete(UUID id) {
        proveedorServicioRepository.deleteById(id);
    }
}
