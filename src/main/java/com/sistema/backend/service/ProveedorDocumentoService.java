package com.sistema.backend.service;

import com.sistema.backend.entity.ProveedorDocumento;
import com.sistema.backend.repository.ProveedorDocumentoRepository;
import com.sistema.backend.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProveedorDocumentoService {

    private final ProveedorDocumentoRepository proveedorDocumentoRepository;
    private final ProveedorRepository proveedorRepository;

    public ProveedorDocumentoService(ProveedorDocumentoRepository proveedorDocumentoRepository,
                                     ProveedorRepository proveedorRepository) {
        this.proveedorDocumentoRepository = proveedorDocumentoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<ProveedorDocumento> getAll() {
        return proveedorDocumentoRepository.findAll();
    }

    public Optional<ProveedorDocumento> getById(UUID id) {
        return proveedorDocumentoRepository.findById(id);
    }

    public List<ProveedorDocumento> getByProveedorId(UUID proveedorId) {
        return proveedorRepository.findById(proveedorId)
                .map(proveedorDocumentoRepository::findByProveedor)
                .orElse(List.of());
    }

    public ProveedorDocumento save(ProveedorDocumento documento) {
        return proveedorDocumentoRepository.save(documento);
    }

    public void delete(UUID id) {
        proveedorDocumentoRepository.deleteById(id);
    }
}
