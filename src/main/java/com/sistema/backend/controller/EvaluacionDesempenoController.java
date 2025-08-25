package com.sistema.backend.controller;

import com.sistema.backend.entity.EvaluacionDesempeno;
import com.sistema.backend.service.EvaluacionDesempenoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/evaluaciones-desempeno")
@CrossOrigin(origins = "*")
public class EvaluacionDesempenoController {

    private final EvaluacionDesempenoService service;

    public EvaluacionDesempenoController(EvaluacionDesempenoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionDesempeno>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDesempeno> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<EvaluacionDesempeno>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(service.getByPersonalId(personalId));
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<EvaluacionDesempeno>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(service.getByProyectoId(proyectoId));
    }

    @GetMapping("/personal/{personalId}/proyecto/{proyectoId}")
    public ResponseEntity<EvaluacionDesempeno> getByPersonalAndProyecto(
            @PathVariable UUID personalId,
            @PathVariable UUID proyectoId) {
        return service.getByPersonalAndProyecto(personalId, proyectoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EvaluacionDesempeno> create(@RequestBody EvaluacionDesempeno evaluacion) {
        return ResponseEntity.ok(service.save(evaluacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDesempeno> update(@PathVariable UUID id, @RequestBody EvaluacionDesempeno updated) {
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
