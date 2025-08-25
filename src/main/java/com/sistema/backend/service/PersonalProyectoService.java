package com.sistema.backend.service;

import com.sistema.backend.entity.PersonalProyecto;
import com.sistema.backend.repository.PersonalProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonalProyectoService {

    private final PersonalProyectoRepository personalProyectoRepository;

    public PersonalProyectoService(PersonalProyectoRepository personalProyectoRepository) {
        this.personalProyectoRepository = personalProyectoRepository;
    }

    public List<PersonalProyecto> getAll() {
        return personalProyectoRepository.findAll();
    }

    public List<PersonalProyecto> getByProyectoId(UUID proyectoId) {
        return personalProyectoRepository.findByProyectoId(proyectoId);
    }

    public List<PersonalProyecto> getByPersonalId(UUID personalId) {
        return personalProyectoRepository.findByPersonalId(personalId);
    }

    public Optional<PersonalProyecto> getById(UUID id) {
        return personalProyectoRepository.findById(id);
    }

    public Optional<PersonalProyecto> getByPersonalAndProyecto(UUID personalId, UUID proyectoId) {
        return personalProyectoRepository.findByPersonalIdAndProyectoId(personalId, proyectoId);
    }

    public PersonalProyecto save(PersonalProyecto personalProyecto) {
        return personalProyectoRepository.save(personalProyecto);
    }

    public void delete(UUID id) {
        personalProyectoRepository.deleteById(id);
    }
}
