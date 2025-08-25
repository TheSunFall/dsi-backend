package com.sistema.backend.service;

import com.sistema.backend.entity.PagoProveedor;
import com.sistema.backend.repository.PagoProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagoProveedorService {

    private final PagoProveedorRepository pagoProveedorRepository;

    public PagoProveedorService(PagoProveedorRepository pagoProveedorRepository) {
        this.pagoProveedorRepository = pagoProveedorRepository;
    }

    public List<PagoProveedor> getAll() {
        return pagoProveedorRepository.findAll();
    }

    public List<PagoProveedor> getByProveedorId(UUID proveedorId) {
        return pagoProveedorRepository.findByProveedorId(proveedorId);
    }

    public List<PagoProveedor> getByProyectoId(UUID proyectoId) {
        return pagoProveedorRepository.findByProyectoId(proyectoId);
    }

    public Optional<PagoProveedor> getById(UUID id) {
        return pagoProveedorRepository.findById(id);
    }

    public PagoProveedor save(PagoProveedor pagoProveedor) {
        return pagoProveedorRepository.save(pagoProveedor);
    }

    public void delete(UUID id) {
        pagoProveedorRepository.deleteById(id);
    }
}
