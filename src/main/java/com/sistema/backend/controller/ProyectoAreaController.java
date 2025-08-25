package com.sistema.backend.controller;

import com.sistema.backend.entity.ProyectoArea;
import com.sistema.backend.service.ProyectoAreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proyecto-areas")
@CrossOrigin(origins = "*")
public class ProyectoAreaController {

    private final ProyectoAreaService proyectoAreaService;

    public ProyectoAreaController(ProyectoAreaService proyectoAreaService) {
        this.proyectoAreaService = proyectoAreaService;
    }

    @GetMapping
    public ResponseEntity<List<ProyectoArea>> getAll() {
        return ResponseEntity.ok(proyectoAreaService.getAll());
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<ProyectoArea>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(proyectoAreaService.getByProyectoId(proyectoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoArea> getById(@PathVariable UUID id) {
        return proyectoAreaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProyectoArea> create(@RequestBody ProyectoArea proyectoArea) {
        return ResponseEntity.ok(proyectoAreaService.save(proyectoArea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoArea> update(@PathVariable UUID id, @RequestBody ProyectoArea updated) {
        return proyectoAreaService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(proyectoAreaService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (proyectoAreaService.getById(id).isPresent()) {
            proyectoAreaService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
