package com.sistema.backend.service;

import com.sistema.backend.entity.Especialidad;
import com.sistema.backend.repository.EspecialidadRepository;
import com.sistema.backend.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final PersonalRepository personalRepository;

    public EspecialidadService(EspecialidadRepository especialidadRepository, PersonalRepository personalRepository) {
        this.especialidadRepository = especialidadRepository;
        this.personalRepository = personalRepository;
    }

    public List<Especialidad> getAll() {
        return especialidadRepository.findAll();
    }

    public Optional<Especialidad> getById(UUID id) {
        return especialidadRepository.findById(id);
    }

    public List<Especialidad> getByPersonalId(UUID personalId) {
        return personalRepository.findById(personalId)
                .map(especialidadRepository::findByPersonal)
                .orElse(List.of());
    }

    public Especialidad save(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public void delete(UUID id) {
        especialidadRepository.deleteById(id);
    }
}
