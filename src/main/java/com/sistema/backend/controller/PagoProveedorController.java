package com.sistema.backend.controller;

import com.sistema.backend.entity.PagoProveedor;
import com.sistema.backend.service.PagoProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pagos-proveedor")
@CrossOrigin(origins = "*")
public class PagoProveedorController {

    private final PagoProveedorService pagoProveedorService;

    public PagoProveedorController(PagoProveedorService pagoProveedorService) {
        this.pagoProveedorService = pagoProveedorService;
    }

    @GetMapping
    public ResponseEntity<List<PagoProveedor>> getAll() {
        return ResponseEntity.ok(pagoProveedorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoProveedor> getById(@PathVariable UUID id) {
        return pagoProveedorService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<PagoProveedor>> getByProveedor(@PathVariable UUID proveedorId) {
        return ResponseEntity.ok(pagoProveedorService.getByProveedorId(proveedorId));
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<PagoProveedor>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(pagoProveedorService.getByProyectoId(proyectoId));
    }

    @PostMapping
    public ResponseEntity<PagoProveedor> create(@RequestBody PagoProveedor pagoProveedor) {
        return ResponseEntity.ok(pagoProveedorService.save(pagoProveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoProveedor> update(@PathVariable UUID id, @RequestBody PagoProveedor updated) {
        return pagoProveedorService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(pagoProveedorService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (pagoProveedorService.getById(id).isPresent()) {
            pagoProveedorService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
