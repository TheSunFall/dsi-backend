package com.sistema.backend.controller;

import com.sistema.backend.entity.ProveedorProyecto;
import com.sistema.backend.service.ProveedorProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proveedor-proyectos")
@CrossOrigin(origins = "*")
public class ProveedorProyectoController {

    private final ProveedorProyectoService proveedorProyectoService;

    public ProveedorProyectoController(ProveedorProyectoService proveedorProyectoService) {
        this.proveedorProyectoService = proveedorProyectoService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorProyecto>> getAll() {
        return ResponseEntity.ok(proveedorProyectoService.getAll());
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ProveedorProyecto>> getByProveedor(@PathVariable UUID proveedorId) {
        return ResponseEntity.ok(proveedorProyectoService.getByProveedorId(proveedorId));
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<ProveedorProyecto>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(proveedorProyectoService.getByProyectoId(proyectoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorProyecto> getById(@PathVariable UUID id) {
        return proveedorProyectoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/check/{proveedorId}/{proyectoId}")
    public ResponseEntity<ProveedorProyecto> getByProveedorAndProyecto(
            @PathVariable UUID proveedorId,
            @PathVariable UUID proyectoId
    ) {
        return proveedorProyectoService.getByProveedorAndProyecto(proveedorId, proyectoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProveedorProyecto> create(@RequestBody ProveedorProyecto proveedorProyecto) {
        return ResponseEntity.ok(proveedorProyectoService.save(proveedorProyecto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorProyecto> update(@PathVariable UUID id, @RequestBody ProveedorProyecto updated) {
        return proveedorProyectoService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(proveedorProyectoService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (proveedorProyectoService.getById(id).isPresent()) {
            proveedorProyectoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
