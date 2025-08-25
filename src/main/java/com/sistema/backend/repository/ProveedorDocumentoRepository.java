package com.sistema.backend.repository;

import com.sistema.backend.entity.Proveedor;
import com.sistema.backend.entity.ProveedorDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProveedorDocumentoRepository extends JpaRepository<ProveedorDocumento, UUID> {
    List<ProveedorDocumento> findByProveedor(Proveedor proveedor);
}
