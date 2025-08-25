package com.sistema.backend.controller;

import com.sistema.backend.entity.Proyecto;
import com.sistema.backend.service.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> getAll() {
        return ResponseEntity.ok(proyectoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable UUID id) {
        return proyectoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Proyecto> getByCodigo(@PathVariable String codigo) {
        return proyectoService.getByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proyecto> create(@RequestBody Proyecto proyecto) {
        return ResponseEntity.ok(proyectoService.save(proyecto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> update(@PathVariable UUID id, @RequestBody Proyecto updated) {
        return proyectoService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(proyectoService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (proyectoService.getById(id).isPresent()) {
            proyectoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
