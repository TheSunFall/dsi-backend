package com.sistema.backend.service;

import com.sistema.backend.entity.PersonalTarea;
import com.sistema.backend.repository.PersonalTareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonalTareaService {

    private final PersonalTareaRepository repository;

    public PersonalTareaService(PersonalTareaRepository repository) {
        this.repository = repository;
    }

    public List<PersonalTarea> getAll() {
        return repository.findAll();
    }

    public Optional<PersonalTarea> getById(UUID id) {
        return repository.findById(id);
    }

    public List<PersonalTarea> getByPersonal(UUID personalId) {
        return repository.findByPersonalId(personalId);
    }

    public List<PersonalTarea> getByProyecto(UUID proyectoId) {
        return repository.findByProyectoId(proyectoId);
    }

    public List<PersonalTarea> getByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public PersonalTarea save(PersonalTarea tarea) {
        return repository.save(tarea);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
