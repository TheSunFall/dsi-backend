package com.sistema.backend.service;

import com.sistema.backend.entity.EvaluacionDesempeno;
import com.sistema.backend.repository.EvaluacionDesempenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EvaluacionDesempenoService {

    private final EvaluacionDesempenoRepository repository;

    public EvaluacionDesempenoService(EvaluacionDesempenoRepository repository) {
        this.repository = repository;
    }

    public List<EvaluacionDesempeno> getAll() {
        return repository.findAll();
    }

    public Optional<EvaluacionDesempeno> getById(UUID id) {
        return repository.findById(id);
    }

    public Optional<EvaluacionDesempeno> getByPersonalAndProyecto(UUID personalId, UUID proyectoId) {
        return repository.findByPersonalIdAndProyectoId(personalId, proyectoId);
    }

    public List<EvaluacionDesempeno> getByPersonalId(UUID personalId) {
        return repository.findByPersonalId(personalId);
    }

    public List<EvaluacionDesempeno> getByProyectoId(UUID proyectoId) {
        return repository.findByProyectoId(proyectoId);
    }

    public EvaluacionDesempeno save(EvaluacionDesempeno evaluacion) {
        return repository.save(evaluacion);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
