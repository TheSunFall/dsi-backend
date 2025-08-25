package com.sistema.backend.controller;

import com.sistema.backend.entity.ProveedorActividad;
import com.sistema.backend.service.ProveedorActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proveedor-actividades")
@CrossOrigin(origins = "*")
public class ProveedorActividadController {

    private final ProveedorActividadService service;

    public ProveedorActividadController(ProveedorActividadService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorActividad>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorActividad> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proveedor-proyecto/{proveedorProyectoId}")
    public ResponseEntity<List<ProveedorActividad>> getByProveedorProyecto(@PathVariable UUID proveedorProyectoId) {
        return ResponseEntity.ok(service.getByProveedorProyecto(proveedorProyectoId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProveedorActividad>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.getByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<ProveedorActividad> create(@RequestBody ProveedorActividad actividad) {
        return ResponseEntity.ok(service.save(actividad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorActividad> update(@PathVariable UUID id, @RequestBody ProveedorActividad updated) {
        return service.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(service.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (service.getById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
