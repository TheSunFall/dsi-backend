package com.sistema.backend.controller;

import com.sistema.backend.entity.GradoAcademico;
import com.sistema.backend.service.GradoAcademicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/grados-academicos")
@CrossOrigin(origins = "*")
public class GradoAcademicoController {

    private final GradoAcademicoService gradoAcademicoService;

    public GradoAcademicoController(GradoAcademicoService gradoAcademicoService) {
        this.gradoAcademicoService = gradoAcademicoService;
    }

    @GetMapping
    public ResponseEntity<List<GradoAcademico>> getAll() {
        return ResponseEntity.ok(gradoAcademicoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradoAcademico> getById(@PathVariable UUID id) {
        return gradoAcademicoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<GradoAcademico>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(gradoAcademicoService.getByPersonalId(personalId));
    }

    @PostMapping
    public ResponseEntity<GradoAcademico> create(@RequestBody GradoAcademico gradoAcademico) {
        return ResponseEntity.ok(gradoAcademicoService.save(gradoAcademico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradoAcademico> update(@PathVariable UUID id, @RequestBody GradoAcademico updated) {
        return gradoAcademicoService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(gradoAcademicoService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (gradoAcademicoService.getById(id).isPresent()) {
            gradoAcademicoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
