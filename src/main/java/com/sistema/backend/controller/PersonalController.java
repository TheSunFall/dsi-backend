package com.sistema.backend.controller;

import com.sistema.backend.entity.Personal;
import com.sistema.backend.service.PersonalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin(origins = "*") // permitir frontend React
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    public ResponseEntity<List<Personal>> getAll() {
        return ResponseEntity.ok(personalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getById(@PathVariable UUID id) {
        return personalService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personal> create(@RequestBody Personal personal) {
        return ResponseEntity.ok(personalService.save(personal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> update(@PathVariable UUID id, @RequestBody Personal updated) {
        return personalService.getById(id)
                .map(existing -> {
                    updated.setId(id); // aseguramos que actualiza
                    return ResponseEntity.ok(personalService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (personalService.getById(id).isPresent()) {
            personalService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
