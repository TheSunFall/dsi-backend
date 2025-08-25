package com.sistema.backend.controller;

import com.sistema.backend.entity.PagoPersonal;
import com.sistema.backend.service.PagoPersonalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pagos-personal")
@CrossOrigin(origins = "*")
public class PagoPersonalController {

    private final PagoPersonalService pagoPersonalService;

    public PagoPersonalController(PagoPersonalService pagoPersonalService) {
        this.pagoPersonalService = pagoPersonalService;
    }

    @GetMapping
    public ResponseEntity<List<PagoPersonal>> getAll() {
        return ResponseEntity.ok(pagoPersonalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoPersonal> getById(@PathVariable UUID id) {
        return pagoPersonalService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<PagoPersonal>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(pagoPersonalService.getByPersonalId(personalId));
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<PagoPersonal>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(pagoPersonalService.getByProyectoId(proyectoId));
    }

    @PostMapping
    public ResponseEntity<PagoPersonal> create(@RequestBody PagoPersonal pagoPersonal) {
        return ResponseEntity.ok(pagoPersonalService.save(pagoPersonal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoPersonal> update(@PathVariable UUID id, @RequestBody PagoPersonal updated) {
        return pagoPersonalService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(pagoPersonalService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (pagoPersonalService.getById(id).isPresent()) {
            pagoPersonalService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
