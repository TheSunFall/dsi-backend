package com.sistema.backend.service;

import com.sistema.backend.entity.PagoPersonal;
import com.sistema.backend.repository.PagoPersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagoPersonalService {

    private final PagoPersonalRepository pagoPersonalRepository;

    public PagoPersonalService(PagoPersonalRepository pagoPersonalRepository) {
        this.pagoPersonalRepository = pagoPersonalRepository;
    }

    public List<PagoPersonal> getAll() {
        return pagoPersonalRepository.findAll();
    }

    public List<PagoPersonal> getByPersonalId(UUID personalId) {
        return pagoPersonalRepository.findByPersonalId(personalId);
    }

    public List<PagoPersonal> getByProyectoId(UUID proyectoId) {
        return pagoPersonalRepository.findByProyectoId(proyectoId);
    }

    public Optional<PagoPersonal> getById(UUID id) {
        return pagoPersonalRepository.findById(id);
    }

    public PagoPersonal save(PagoPersonal pagoPersonal) {
        return pagoPersonalRepository.save(pagoPersonal);
    }

    public void delete(UUID id) {
        pagoPersonalRepository.deleteById(id);
    }
}
