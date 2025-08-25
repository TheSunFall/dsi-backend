package com.sistema.backend.controller;

import com.sistema.backend.entity.ProveedorServicio;
import com.sistema.backend.service.ProveedorServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proveedor-servicios")
@CrossOrigin(origins = "*")
public class ProveedorServicioController {

    private final ProveedorServicioService proveedorServicioService;

    public ProveedorServicioController(ProveedorServicioService proveedorServicioService) {
        this.proveedorServicioService = proveedorServicioService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorServicio>> getAll() {
        return ResponseEntity.ok(proveedorServicioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorServicio> getById(@PathVariable UUID id) {
        return proveedorServicioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ProveedorServicio>> getByProveedor(@PathVariable UUID proveedorId) {
        return ResponseEntity.ok(proveedorServicioService.getByProveedorId(proveedorId));
    }

    @PostMapping
    public ResponseEntity<ProveedorServicio> create(@RequestBody ProveedorServicio servicio) {
        return ResponseEntity.ok(proveedorServicioService.save(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorServicio> update(@PathVariable UUID id, @RequestBody ProveedorServicio updated) {
        return proveedorServicioService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(proveedorServicioService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (proveedorServicioService.getById(id).isPresent()) {
            proveedorServicioService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
