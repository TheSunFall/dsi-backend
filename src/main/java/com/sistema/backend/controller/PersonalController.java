package com.sistema.backend.controller;

import com.sistema.backend.dto.CreatePersonalDTO;
import com.sistema.backend.dto.PersonalDTO;
import com.sistema.backend.service.PersonalService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<PersonalDTO>> getAll() {
        return ResponseEntity.ok(personalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonalDTO> create(@Valid @RequestBody CreatePersonalDTO dto) {
        return ResponseEntity.ok(personalService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
