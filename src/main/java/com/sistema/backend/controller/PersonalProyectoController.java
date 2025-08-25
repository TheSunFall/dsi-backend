package com.sistema.backend.controller;

import com.sistema.backend.entity.PersonalProyecto;
import com.sistema.backend.service.PersonalProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personal-proyectos")
@CrossOrigin(origins = "*")
public class PersonalProyectoController {

    private final PersonalProyectoService personalProyectoService;

    public PersonalProyectoController(PersonalProyectoService personalProyectoService) {
        this.personalProyectoService = personalProyectoService;
    }

    @GetMapping
    public ResponseEntity<List<PersonalProyecto>> getAll() {
        return ResponseEntity.ok(personalProyectoService.getAll());
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<PersonalProyecto>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(personalProyectoService.getByProyectoId(proyectoId));
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<PersonalProyecto>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(personalProyectoService.getByPersonalId(personalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalProyecto> getById(@PathVariable UUID id) {
        return personalProyectoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/check/{personalId}/{proyectoId}")
    public ResponseEntity<PersonalProyecto> getByPersonalAndProyecto(
            @PathVariable UUID personalId,
            @PathVariable UUID proyectoId
    ) {
        return personalProyectoService.getByPersonalAndProyecto(personalId, proyectoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonalProyecto> create(@RequestBody PersonalProyecto personalProyecto) {
        return ResponseEntity.ok(personalProyectoService.save(personalProyecto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalProyecto> update(@PathVariable UUID id, @RequestBody PersonalProyecto updated) {
        return personalProyectoService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(personalProyectoService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (personalProyectoService.getById(id).isPresent()) {
            personalProyectoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
