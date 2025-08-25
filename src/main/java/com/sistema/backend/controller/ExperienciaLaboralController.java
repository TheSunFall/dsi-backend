package com.sistema.backend.controller;

import com.sistema.backend.entity.ExperienciaLaboral;
import com.sistema.backend.service.ExperienciaLaboralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/experiencia-laboral")
@CrossOrigin(origins = "*")
public class ExperienciaLaboralController {

    private final ExperienciaLaboralService experienciaLaboralService;

    public ExperienciaLaboralController(ExperienciaLaboralService experienciaLaboralService) {
        this.experienciaLaboralService = experienciaLaboralService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaLaboral>> getAll() {
        return ResponseEntity.ok(experienciaLaboralService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaLaboral> getById(@PathVariable UUID id) {
        return experienciaLaboralService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<ExperienciaLaboral>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(experienciaLaboralService.getByPersonalId(personalId));
    }

    @PostMapping
    public ResponseEntity<ExperienciaLaboral> create(@RequestBody ExperienciaLaboral experienciaLaboral) {
        return ResponseEntity.ok(experienciaLaboralService.save(experienciaLaboral));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaLaboral> update(@PathVariable UUID id, @RequestBody ExperienciaLaboral updated) {
        return experienciaLaboralService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(experienciaLaboralService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (experienciaLaboralService.getById(id).isPresent()) {
            experienciaLaboralService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
