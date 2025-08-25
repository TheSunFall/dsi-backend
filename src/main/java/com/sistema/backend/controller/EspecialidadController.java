package com.sistema.backend.controller;

import com.sistema.backend.entity.Especialidad;
import com.sistema.backend.service.EspecialidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/especialidades")
@CrossOrigin(origins = "*")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public ResponseEntity<List<Especialidad>> getAll() {
        return ResponseEntity.ok(especialidadService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getById(@PathVariable UUID id) {
        return especialidadService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<Especialidad>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(especialidadService.getByPersonalId(personalId));
    }

    @PostMapping
    public ResponseEntity<Especialidad> create(@RequestBody Especialidad especialidad) {
        return ResponseEntity.ok(especialidadService.save(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> update(@PathVariable UUID id, @RequestBody Especialidad updated) {
        return especialidadService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(especialidadService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (especialidadService.getById(id).isPresent()) {
            especialidadService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

