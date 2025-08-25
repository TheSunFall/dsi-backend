package com.sistema.backend.controller;

import com.sistema.backend.entity.Auditoria;
import com.sistema.backend.service.AuditoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auditoria")
@CrossOrigin(origins = "*")
public class AuditoriaController {

    private final AuditoriaService service;

    public AuditoriaController(AuditoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Auditoria>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auditoria> getById(@PathVariable UUID id) {
        Auditoria auditoria = service.getById(id);
        return auditoria != null ? ResponseEntity.ok(auditoria) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tabla/{tabla}")
    public ResponseEntity<List<Auditoria>> getByTabla(@PathVariable String tabla) {
        return ResponseEntity.ok(service.getByTabla(tabla));
    }

    @GetMapping("/registro/{registroId}")
    public ResponseEntity<List<Auditoria>> getByRegistroId(@PathVariable String registroId) {
        return ResponseEntity.ok(service.getByRegistroId(registroId));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<Auditoria>> getByUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(service.getByUsuario(usuario));
    }

    @PostMapping
    public ResponseEntity<Auditoria> create(@RequestBody Auditoria auditoria) {
        return ResponseEntity.ok(service.save(auditoria));
    }
}
