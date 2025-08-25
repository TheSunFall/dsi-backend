package com.sistema.backend.controller;

import com.sistema.backend.entity.PersonalTarea;
import com.sistema.backend.service.PersonalTareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personal-tareas")
@CrossOrigin(origins = "*")
public class PersonalTareaController {

    private final PersonalTareaService service;

    public PersonalTareaController(PersonalTareaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PersonalTarea>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalTarea> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<PersonalTarea>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(service.getByPersonal(personalId));
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<PersonalTarea>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(service.getByProyecto(proyectoId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PersonalTarea>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.getByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<PersonalTarea> create(@RequestBody PersonalTarea tarea) {
        return ResponseEntity.ok(service.save(tarea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalTarea> update(@PathVariable UUID id, @RequestBody PersonalTarea updated) {
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
