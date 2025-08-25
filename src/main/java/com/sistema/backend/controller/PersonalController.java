package com.sistema.backend.controller;

import com.sistema.backend.entity.Personal;
import com.sistema.backend.service.PersonalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    public List<Personal> getAll() {
        return personalService.findAll();
    }

    @GetMapping("/{id}")
    public Personal getById(@PathVariable Long id) {
        return personalService.findById(id);
    }

    @PostMapping
    public Personal create(@RequestBody Personal personal) {
        return personalService.create(personal);
    }

    @PutMapping("/{id}")
    public Personal update(@PathVariable Long id, @RequestBody Personal personal) {
        return personalService.update(id, personal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
