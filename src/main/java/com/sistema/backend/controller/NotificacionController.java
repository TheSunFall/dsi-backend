package com.sistema.backend.controller;

import com.sistema.backend.entity.Notificacion;
import com.sistema.backend.service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getById(@PathVariable UUID id) {
        Notificacion notificacion = service.getById(id);
        return notificacion != null ? ResponseEntity.ok(notificacion) : ResponseEntity.notFound().build();
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<Notificacion>> getByPersonal(@PathVariable UUID personalId) {
        return ResponseEntity.ok(service.getByPersonal(personalId));
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<Notificacion>> getByProyecto(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(service.getByProyecto(proyectoId));
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<Notificacion>> getByProveedor(@PathVariable UUID proveedorId) {
        return ResponseEntity.ok(service.getByProveedor(proveedorId));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Notificacion>> getByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.getByTipo(tipo));
    }

    @GetMapping("/leidas/{leida}")
    public ResponseEntity<List<Notificacion>> getByLeida(@PathVariable boolean leida) {
        return ResponseEntity.ok(service.getByLeida(leida));
    }

    @PostMapping
    public ResponseEntity<Notificacion> create(@RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(service.save(notificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> update(@PathVariable UUID id, @RequestBody Notificacion notificacion) {
        Notificacion existente = service.getById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        notificacion.setId(id);
        return ResponseEntity.ok(service.save(notificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
