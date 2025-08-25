package com.sistema.backend.service;

import com.sistema.backend.entity.Proveedor;
import com.sistema.backend.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> getAll() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> getById(UUID id) {
        return proveedorRepository.findById(id);
    }

    public Optional<Proveedor> getByCodigo(String codigo) {
        return proveedorRepository.findByCodigo(codigo);
    }

    public Optional<Proveedor> getByRuc(String ruc) {
        return proveedorRepository.findByRuc(ruc);
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void delete(UUID id) {
        proveedorRepository.deleteById(id);
    }
}
