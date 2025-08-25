package com.sistema.backend.service;

import com.sistema.backend.entity.Auditoria;
import com.sistema.backend.repository.AuditoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuditoriaService {

    private final AuditoriaRepository repository;

    public AuditoriaService(AuditoriaRepository repository) {
        this.repository = repository;
    }

    public List<Auditoria> getAll() {
        return repository.findAll();
    }

    public Auditoria getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<Auditoria> getByTabla(String tabla) {
        return repository.findByTabla(tabla);
    }

    public List<Auditoria> getByRegistroId(String registroId) {
        return repository.findByRegistroId(registroId);
    }

    public List<Auditoria> getByUsuario(String usuario) {
        return repository.findByUsuario(usuario);
    }

    public Auditoria save(Auditoria auditoria) {
        return repository.save(auditoria);
    }
}
