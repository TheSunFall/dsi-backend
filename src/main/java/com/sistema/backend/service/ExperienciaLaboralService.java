package com.sistema.backend.service;

import com.sistema.backend.entity.ExperienciaLaboral;
import com.sistema.backend.repository.ExperienciaLaboralRepository;
import com.sistema.backend.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExperienciaLaboralService {

    private final ExperienciaLaboralRepository experienciaLaboralRepository;
    private final PersonalRepository personalRepository;

    public ExperienciaLaboralService(ExperienciaLaboralRepository experienciaLaboralRepository, PersonalRepository personalRepository) {
        this.experienciaLaboralRepository = experienciaLaboralRepository;
        this.personalRepository = personalRepository;
    }

    public List<ExperienciaLaboral> getAll() {
        return experienciaLaboralRepository.findAll();
    }

    public Optional<ExperienciaLaboral> getById(UUID id) {
        return experienciaLaboralRepository.findById(id);
    }

    public List<ExperienciaLaboral> getByPersonalId(UUID personalId) {
        return personalRepository.findById(personalId)
                .map(experienciaLaboralRepository::findByPersonal)
                .orElse(List.of());
    }

    public ExperienciaLaboral save(ExperienciaLaboral experienciaLaboral) {
        return experienciaLaboralRepository.save(experienciaLaboral);
    }

    public void delete(UUID id) {
        experienciaLaboralRepository.deleteById(id);
    }
}
