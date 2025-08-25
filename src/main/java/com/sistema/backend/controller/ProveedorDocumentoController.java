package com.sistema.backend.controller;

import com.sistema.backend.entity.ProveedorDocumento;
import com.sistema.backend.service.ProveedorDocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proveedor-documentos")
@CrossOrigin(origins = "*")
public class ProveedorDocumentoController {

    private final ProveedorDocumentoService proveedorDocumentoService;

    public ProveedorDocumentoController(ProveedorDocumentoService proveedorDocumentoService) {
        this.proveedorDocumentoService = proveedorDocumentoService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDocumento>> getAll() {
        return ResponseEntity.ok(proveedorDocumentoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDocumento> getById(@PathVariable UUID id) {
        return proveedorDocumentoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ProveedorDocumento>> getByProveedor(@PathVariable UUID proveedorId) {
        return ResponseEntity.ok(proveedorDocumentoService.getByProveedorId(proveedorId));
    }

    @PostMapping
    public ResponseEntity<ProveedorDocumento> create(@RequestBody ProveedorDocumento documento) {
        return ResponseEntity.ok(proveedorDocumentoService.save(documento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDocumento> update(@PathVariable UUID id, @RequestBody ProveedorDocumento updated) {
        return proveedorDocumentoService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(proveedorDocumentoService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (proveedorDocumentoService.getById(id).isPresent()) {
            proveedorDocumentoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
