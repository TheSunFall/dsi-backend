package com.sistema.backend.controller;

import com.sistema.backend.entity.DatosLaborales;
import com.sistema.backend.service.DatosLaboralesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/datos-laborales")
@CrossOrigin(origins = "*")
public class DatosLaboralesController {

    private final DatosLaboralesService datosLaboralesService;

    public DatosLaboralesController(DatosLaboralesService datosLaboralesService) {
        this.datosLaboralesService = datosLaboralesService;
    }

    @GetMapping
    public ResponseEntity<List<DatosLaborales>> getAll() {
        return ResponseEntity.ok(datosLaboralesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosLaborales> getById(@PathVariable UUID id) {
        return datosLaboralesService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<DatosLaborales>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(datosLaboralesService.getByPersonalId(personalId));
    }

    @PostMapping
    public ResponseEntity<DatosLaborales> create(@RequestBody DatosLaborales datosLaborales) {
        return ResponseEntity.ok(datosLaboralesService.save(datosLaborales));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosLaborales> update(@PathVariable UUID id, @RequestBody DatosLaborales updated) {
        return datosLaboralesService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(datosLaboralesService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (datosLaboralesService.getById(id).isPresent()) {
            datosLaboralesService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
